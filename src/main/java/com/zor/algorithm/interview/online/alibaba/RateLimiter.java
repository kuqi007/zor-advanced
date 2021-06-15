package com.zor.algorithm.interview.online.alibaba;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2021/6/16 阿里笔试
 * 题目2.接口限流实现
 * 有一个API网关，出于对API接口的保护，需要建立一个流控功能，根据API名称，每分钟最多只能请求指定的次数（如1000次），超过限制则这分钟内返回错误，但下一分钟又可以正常请求。
 * Created by kuqi0 on 2021/6/15
 */
public class RateLimiter {

    private static final int limit = 50;

    public static void main(String[] args) throws InterruptedException {

        // 为什么不能一直跑？？？，想看到第二分钟


        int i = 0;
        while (i++ < 2) {

            mockRequest();


            //Thread.sleep(60 * 1000);

        }
    }

    private static void mockRequest() throws InterruptedException {
        RateLimiter rt = new RateLimiter();
        //rt.timer();
        for (int j = 0; j < 60; j++) {
            String result = rt.requestApi();
            if (result.equals("请求失败")) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(100);
            }
            System.out.println(result);
            //Thread.yield();
        }
    }

    /**
     * 有一个API网关，出于对API接口的保护，需要建立一个流控功能，根据API名称，每分钟最多只能请求指定的次数（如1000次），超过限制则这分钟内返回错误，但下一分钟又可以正常请求。
     */
    private final AtomicInteger counter = new AtomicInteger(0);

    private volatile Long startTime;

    /**
     * 无需开新线程版
     */
    public String requestApi() {
        if (startTime == null) {
            startTime = System.currentTimeMillis();
        }
        long now = System.currentTimeMillis();
        // 如果已经超过一分钟，重新计算
        if (now - startTime >= 60 * 1000) {
            startTime = now;
            counter.set(0);
        }

        if (counter.get() >= limit) {
            return "请求失败";
        }

        System.out.println("一分钟已经请求了" + counter.incrementAndGet() + "次");
        return "请求成功";
    }

    public String request() {

        if (counter.get() >= limit) {
            return "请求失败";
            //throw new RuntimeException("限流命中");
        }

        System.out.println("一分钟已经请求了" + counter.incrementAndGet() + "次");
        return "请求成功";
    }

    private void timer() {
        if (startTime == null) {
            startTime = System.currentTimeMillis();
        }
        Thread thread = new Thread(() -> {
            while (true) {
                if (System.currentTimeMillis() - startTime >= 60 * 1000) {
                    // reset
                    startTime = System.currentTimeMillis();
                    counter.set(0);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 设置守护线程，让jvm执行完能退出
        thread.setDaemon(true);
        thread.start();
    }
}
