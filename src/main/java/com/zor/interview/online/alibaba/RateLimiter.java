package com.zor.interview.online.alibaba;

import org.junit.Test;

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
        //int i = 0;
        //while (i++ < 1) {

        //mockRequestApi();


        //Thread.sleep(60 * 1000);

        //}
    }

    /**
     * 计数器
     */
    private final AtomicInteger counter = new AtomicInteger(0);

    /**
     * 循环记录的起始时间，如果超过一分钟，时间重置
     */
    private volatile Long startTime;


    /*=================================第一种写法，无需开启新线程，每次请求时判断如果时间超过一分钟则刷新时间和计数器=================================*/
    @Test
    public void mockRequestApi() throws InterruptedException {
        RateLimiter rt = new RateLimiter();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 200; j++) {
            String response;
            String code = rt.requestApi();
            if (code.equals("200")) {
                response = "请求成功";
                Thread.sleep(100);
            } else {
                response = "请求失败";
                Thread.sleep(1000);
            }
            System.out.println("当前耗时：" + (System.currentTimeMillis() - start) + " ms");
            System.out.println("第" + (j + 1) + "次请求结果：" + response);
        }
    }

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
            return "200";
        } else {
            counter.incrementAndGet();
            return counter.get() > limit ? "500" : "200";
        }

        //if (counter.get() >= limit) {
        //    return "500";
        //}

        //int i = counter.incrementAndGet();

        //System.out.println("一分钟已经请求了" + i + "次");
        //return "200";
    }

    /*=================================第二种写法，开启新线程刷新counter=================================*/
    @Test
    public void mockRequest1() throws InterruptedException {
        timer();
        int i = 0;
        while (i++ < 2) {
            for (int j = 0; j < 60; j++) {
                String res = request();
                if (res.equals("请求失败")) {
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(100);
                }
                System.out.println(res);
            }
        }
    }

    public String request() {

        if (counter.get() >= limit) {
            return "请求失败";
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
        // 设置守护线程，让主进程执行完能退出
        thread.setDaemon(true);
        thread.start();
    }
}
