package com.zor.advanced.ratelimiter.advanced;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by kuqi0 on 2021/6/27
 */
public class LeakBucketRateLimiter implements MyRateLimiter {
    // 桶的容量
    private final double capacity;
    // 漏出速率
    private final double permitsPerSecond;
    // 剩余水量
    private double leftWater;
    // 上次注入时间
    private long timestamp = System.currentTimeMillis();

    public LeakBucketRateLimiter(double capacity, double permitsPerSecond) {
        this.capacity = capacity;
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public boolean tryAcquire() {
        // 计算剩余流量
        long now = System.currentTimeMillis();
        // leftWater有可已经是0，所以减完会变成负数，所以要和0取个最大值
        leftWater = Math.max(0, leftWater - ((now - timestamp) / 1000d * permitsPerSecond));
        System.out.println("leftWater = " + leftWater);
        timestamp = now;

        // 如果未满，则放行；否则限流
        if (leftWater < capacity) {
            leftWater++;
            return true;
        } else {
            return false;
        }
    }


    @Test
    public static void testLeakBucketRateLimiter() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        LeakBucketRateLimiter rateLimiter = new LeakBucketRateLimiter(20, 1);
        // 存储流量的队列
        Queue<Integer> queue = new LinkedList<>();
        // 模拟请求  不确定速率注水
        singleThread.execute(() -> {
            int index = 0;
            while (true) {
                index++;
                boolean flag = rateLimiter.tryAcquire();
                if (flag) {
                    queue.offer(index);
                    System.out.println(index + "--------流量被放行----------");
                } else {
                    System.out.println(index + "流量被限制");
                }
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 模拟处理请求 固定速率漏水
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!queue.isEmpty()) {
                System.out.println(queue.poll() + "被处理");
            }
        }, 0, 100, TimeUnit.MILLISECONDS);

        // 保证主线程不会退出
        while (true) {
            Thread.sleep(10000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //// 将时间分片，seconds是时间窗口大小，max代表seconds时间最大访问上限
        //MyRateLimiter myRateLimiter = new LeakBucketRateLimiter(20, 1);
        //
        //int requestCnt = 60;
        //AtomicInteger successCnt = new AtomicInteger();
        //long start = System.currentTimeMillis();
        //for (int i = 0; i < requestCnt; i++) {
        //    boolean result = myRateLimiter.tryAcquire();
        //    if (result) {
        //        successCnt.incrementAndGet();
        //    }
        //    String resultStr = result ? "请求成功" : "请求失败";
        //    long now = System.currentTimeMillis();
        //    System.out.println("第" + (i + 1) + "次请求结果：" + resultStr + "，耗时：" + (now - start));
        //    try {
        //        Thread.sleep(500);
        //    } catch (InterruptedException e) {
        //        e.printStackTrace();
        //    }
        //}
        //System.out.println("全部请求结束，成功率：" + (successCnt.get() * 100 / requestCnt) + "%");

        testLeakBucketRateLimiter();
    }
}
