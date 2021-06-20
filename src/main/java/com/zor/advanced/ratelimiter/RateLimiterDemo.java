package com.zor.advanced.ratelimiter;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by kuqi0 on 2021/6/20
 */
public class RateLimiterDemo {
    public static void main(String[] args) {
        RateLimiterDemo rateLimiterDemo = new RateLimiterDemo();
        for (int i = 0; i < 120; i++) {
            boolean request = rateLimiterDemo.request();
            System.out.println(request);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }

    public boolean request() {
        RateLimiter rateLimiter = RateLimiter.create(2);
        return rateLimiter.tryAcquire();

    }
}
