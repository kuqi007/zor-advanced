package com.zor.advanced.ratelimiter;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口计数器
 * 该算法算的上是固定窗口计数器算法的升级版。滑动窗口计数器算法相比于固定窗口计数器算法的优化在于：它把时间以一定比例分片。
 * 例如我们的接口限流每分钟处理60个请求，我们可以把 1 分钟分为60个窗口。每隔1秒移动一次，每个窗口一秒只能处理 不大于 60(请求数)/60（窗口数） 的请求，
 * 如果当前窗口的请求计数总和超过了限制的数量的话就不再处理其他请求。
 * Created by kuqi0 on 2021/6/20
 */
public class TimeWindow {

    private final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    /**
     * 时间窗口间隔秒数
     */
    private final int seconds;

    /**
     * 时间窗口最大限流
     */
    private final int max;


    public TimeWindow(int max, int seconds) {
        this.seconds = seconds;
        this.max = max;

        /*
         * 永续线程执行清理queue任务
         */
        Thread cleanThread = new Thread(() -> {
            while (true) {
                try {
                    // 等待 间隔秒数-1 执行清理操作
                    Thread.sleep((seconds - 1) * 1000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                clean();
            }
        });
        // 设置为守护线程
        cleanThread.setDaemon(true);
        cleanThread.start();
    }

    public static void main(String[] args) {
        // 将时间分片，seconds是时间窗口大小，max代表seconds时间最大访问上限
        TimeWindow timeWindow = new TimeWindow(1, 1);

        int requestCnt = 60;
        AtomicInteger successCnt = new AtomicInteger();
        long start = System.currentTimeMillis();
        for (int i = 0; i < requestCnt; i++) {
            boolean result = timeWindow.take();
            if (result) {
                successCnt.incrementAndGet();
            }
            String resultStr = result ? "请求成功" : "请求失败";
            long now = System.currentTimeMillis();
            System.out.println("第" + (i + 1) + "次请求结果：" + resultStr + "，耗时：" + (now - start));
            try {
                Thread.sleep(new Random().nextInt(20) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("全部请求结束，成功率：" + (successCnt.get() * 100 / requestCnt) + "%");
    }

    /**
     * 获取令牌，并且添加时间
     */
    public boolean take() {
        long now = System.currentTimeMillis();

        int curCnt = sizeOfValid();
        System.out.println("当前窗口请求数量：" + curCnt + "，最大数量：" + max);
        if (curCnt > max) {
            System.out.println("超限，请求拒绝");
            return false;
        } else {
            // 将当前请求时间放入队列
            this.queue.offer(now);
        }
        return true;
    }


    /**
     * 获取当前时间窗口总访问数
     */
    public int sizeOfValid() {
        Iterator<Long> it = queue.iterator();
        // 当前窗口的起点
        long windowStart = System.currentTimeMillis() - seconds * 1000L;
        int count = 0;
        while (it.hasNext()) {
            long t = it.next();
            if (t > windowStart) {
                // 在当前的统计时间范围内
                count++;
            }
        }
        return count;
    }

    /**
     * 清理过期的时间
     */
    private void clean() {
        // 当前窗口的起点，如果小于起点，表示失效，清理该数据
        long curWindowStart = System.currentTimeMillis() - seconds * 1000L;
        int before = queue.size();
        boolean hasClean = false;
        Long tl;
        while ((tl = queue.peek()) != null && tl < curWindowStart) {
            System.out.println("清理过期数据");
            queue.poll();
            hasClean = true;
        }
        int after = queue.size();
        if (hasClean) {
            System.out.println("队列清理之前数量：" + before + "，清理之后：" + after);
        }
    }
}
