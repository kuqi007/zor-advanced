package com.zor.algorithm.interview.online;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThread2 {
    private static AtomicInteger num = new AtomicInteger(1);

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (num.intValue() < 100) {
                if (num.intValue() % 2 == 1) {
                    System.out.println("奇数线程:" + num.intValue());
                    num.incrementAndGet();
                }
                countDownLatch.countDown();
                long count = countDownLatch.getCount();
            }
        });

        Thread t2 = new Thread(() -> {
            while (num.intValue() <= 100) {
                if (num.intValue() % 2 == 0) {
                    System.out.println("偶数线程:" + num.intValue());
                    num.incrementAndGet();
                }
                countDownLatch.countDown();
            }
        });

        t1.start();
        t2.start();
        countDownLatch.await();
    }
}
