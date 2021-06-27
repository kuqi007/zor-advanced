package com.zor.advanced.ratelimiter.simple;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定窗口计数器算法
 * 该算法规定我们单位时间处理的请求数量。比如我们规定我们的一个接口一分钟只能访问10次的话。
 * 使用固定窗口计数器算法的话可以这样实现：给定一个变量counter来记录处理的请求数量，当1分钟之内处理一个请求之后counter+1，
 * 1分钟之内的如果counter=100的话，后续的请求就会被全部拒绝。等到 1分钟结束后，将counter回归成0，重新开始计数（ps：只要过了一个周期就讲counter回归成0）。
 *
 * 这种限流算法无法保证限流速率，因而无法保证突然激增的流量。比如我们限制一个接口一分钟只能访问10次的话，前半分钟一个请求没有接收，后半分钟接收了10个请求。
 * Created by kuqi0 on 2021/6/17
 */
public class Counter {

    /**
     * 最大访问数量
     */
    private static final int limit = 10;
    /**
     * 访问时间差
     */
    private static final long timeout = 1000;
    /**
     * 请求时间
     */
    private volatile Long time;
    /**
     * 当前计数器
     */
    private final AtomicInteger reqCount = new AtomicInteger(0);

    public boolean limit() {
        // 初始化time
        if (time == null) {
            time = System.currentTimeMillis();
        }
        long now = System.currentTimeMillis();
        if (now < time + timeout) {
            // 单位时间内
            reqCount.addAndGet(1);
            return reqCount.get() <= limit;
        } else {
            time = now;
            reqCount.set(0);
            return true;
        }
    }

    @Test
    public void test() throws InterruptedException {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            boolean res = limit();
            System.out.println("第" + (i + 1) + "次请求结果：" + (res ? "请求成功" : "请求失败") + "，当前时间：" + (System.currentTimeMillis() - start) + " ms");
            if (!res) {
                Thread.sleep(timeout / 10);
            } else {
                Thread.sleep(timeout / 100);
            }
        }
    }

}
