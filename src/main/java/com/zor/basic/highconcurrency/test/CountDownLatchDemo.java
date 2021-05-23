package com.zor.basic.highconcurrency.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by kuqi0 on 2021/5/22
 */
public class CountDownLatchDemo {

    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {
        ThreadTest t1 = new ThreadTest(1, countDownLatch);
        ThreadTest t2 = new ThreadTest(2, countDownLatch);
        ThreadTest t3 = new ThreadTest(3, countDownLatch);
        t1.start();
        t2.start();
        t3.start();
        System.out.println("主线程等待子线程执行=====");
        long l = System.currentTimeMillis();
        countDownLatch.await();
        long l1 = System.currentTimeMillis();
        System.out.println("等待结束，耗时：" + (l1 - l) + "ms");
    }


}

class ThreadTest extends Thread {
    private final Integer number;
    private final CountDownLatch countDownLatch;

    public ThreadTest(Integer number, CountDownLatch countDownLatch) {
        this.number = number;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("线程" + number + "正在执行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
