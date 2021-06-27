package com.zor.advanced.ratelimiter.advanced;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口（又称计数器算法，Fixed Window）限流算法
 * Created by kuqi0 on 2021/6/27
 */
public class CounterRateLimiter implements MyRateLimiter {

    /**
     * 每秒限制请求数
     */
    private final long permitsPerSecond;

    /**
     * 超时时间
     */
    private final long timeout;

    /**
     * 上一个窗口的开始时间
     */
    private volatile long timestamp = System.currentTimeMillis();

    /**
     * 计数器
     */
    private final AtomicInteger counter = new AtomicInteger(0);


    public CounterRateLimiter(long permitsPerSecond, long timeout) {
        this.permitsPerSecond = permitsPerSecond;
        this.timeout = timeout;
    }

    @Override
    public boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - timestamp < timeout) {
            // 窗口内请求数量小于阈值，更新计数放行，否则拒绝请求
            counter.incrementAndGet();
            return counter.get() <= permitsPerSecond;
        } else {
            // 时间窗口过期，重置计数器和时间戳
            timestamp = now;
            counter.set(0);
            // 窗口刷新这一次也算请求成功
            counter.incrementAndGet();
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int timeout = 1000;
        int permitsPerSecond = 10;
        MyRateLimiter rateLimiter = new CounterRateLimiter(permitsPerSecond, timeout);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            boolean res = rateLimiter.tryAcquire();
            System.out.println("第" + (i + 1) + "次请求结果：" + (res ? "请求成功" : "请求失败") + "，当前时间：" + (System.currentTimeMillis() - start) + " ms");
            if (!res) {
                Thread.sleep(timeout / 10);
            } else {
                Thread.sleep(timeout / 100);
            }
        }
    }
}
