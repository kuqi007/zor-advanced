package com.zor.advanced.ratelimter;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器
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
