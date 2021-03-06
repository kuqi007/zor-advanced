package com.zor.advanced.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Guava RateLimiter Demo
 * Created by kuqi0 on 2021/6/20
 */
public class GuavaRateLimiterDemo {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(60);
        GuavaRateLimiterDemo rateLimiterDemo = new GuavaRateLimiterDemo();
        for (int i = 0; i < 120; i++) {
            //boolean request = rateLimiterDemo.request();
            boolean b = rateLimiter.tryAcquire();
            if (b) {
                System.out.println("请求成功");
            } else {
                System.out.println("请求失败");
            }
            try {
            Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    //public boolean request() {
    //    RateLimiter rateLimiter = RateLimiter.create(2);
    //    rateLimiter.acquire();
    //}
}
