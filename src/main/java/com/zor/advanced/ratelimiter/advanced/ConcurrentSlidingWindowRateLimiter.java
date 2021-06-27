package com.zor.advanced.ratelimiter.advanced;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 滑动窗口计数器ConcurrentLinkedQueue版，因为ConcurrentLinkedQueue已经保证了线程安全，所以无需加锁
 * Created by kuqi0 on 2021/6/27
 */
public class ConcurrentSlidingWindowRateLimiter implements MyRateLimiter {

    /**
     * 每分钟限制请求数
     */
    private final long permitsPerMinute;

    /**
     * 时间窗口间隔秒数
     */
    private final int seconds;

    /**
     * 保存时间戳的队列
     */
    private final ConcurrentLinkedQueue<Long> queue;

    public ConcurrentSlidingWindowRateLimiter(long permitsPerMinute, int seconds) {
        this.permitsPerMinute = permitsPerMinute;
        this.seconds = seconds;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean tryAcquire() {

        long currentTimeMillis = System.currentTimeMillis();

        int currentWindowCount = getCurrentWindowCount(currentTimeMillis);
        System.out.println("当前窗口计数：" + currentWindowCount);
        if (currentWindowCount >= permitsPerMinute) {
            return false;
        }
        // 保存当前时间到队列
        saveCurrentTime(currentTimeMillis);
        return true;
    }


    private void saveCurrentTime(long currentTimeMillis) {
        queue.offer(currentTimeMillis);
    }

    private int getCurrentWindowCount(long currentTimesMills) {
        // 计算出窗口的开始位置时间
        long startTime = currentTimesMills - (seconds * 1000L);
        int result = 0;
        // 遍历当前存储的队列，删除无效的元素，并累加当前窗口中的所有计数器之和
        Iterator<Long> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Long time = iterator.next();
            if (time < startTime) {
                iterator.remove();
            } else {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 将时间分片，seconds是时间窗口大小，max代表seconds时间最大访问上限
        MyRateLimiter myRateLimiter = new ConcurrentSlidingWindowRateLimiter(10, 2);

        int requestCnt = 60;
        AtomicInteger successCnt = new AtomicInteger();
        long start = System.currentTimeMillis();
        for (int i = 0; i < requestCnt; i++) {
            boolean result = myRateLimiter.tryAcquire();
            if (result) {
                successCnt.incrementAndGet();
            }
            String resultStr = result ? "请求成功" : "请求失败";
            long now = System.currentTimeMillis();
            System.out.println("第" + (i + 1) + "次请求结果：" + resultStr + "，耗时：" + (now - start));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("全部请求结束，成功率：" + (successCnt.get() * 100 / requestCnt) + "%");
    }
}
