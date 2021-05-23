package com.zor.basic.highconcurrency.threadprint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程打印奇偶数
 * 参考https://zhuanlan.zhihu.com/p/47948392
 */
public class TestThread2 {


    public static void main(String[] args) throws InterruptedException {
        test3();
    }


    private static int num = 0;

    /**
     * synchronized wait notify
     */
    private static void test3() {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 == 0) {
                        System.out.println("偶数线程" + num);
                        num++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                while (num <= 100) {
                    if (num % 2 == 1) {
                        System.out.println("奇数线程" + num);
                        num++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    private volatile static int flag = 0;
    private volatile static int i = 0;

    /**
     * volatile关键字
     */
    private static void test2() {
        Thread thread1 = new Thread(() -> {
            while (i <= 100) {
                if (flag == 0) {
                    System.out.println("偶数线程打印" + i);
                    i++;
                    flag = 1;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (i <= 100) {
                if (flag == 1) {
                    System.out.println("奇数线程打印" + i);
                    i++;
                    flag = 0;
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    private static final AtomicInteger atomicInt = new AtomicInteger(0);

    /**
     * 原子类
     */
    private static void test1() {
        Thread t1 = new Thread(() -> {
            while (atomicInt.intValue() < 100) {
                if (atomicInt.intValue() % 2 == 1) {
                    System.out.println("奇数线程:" + atomicInt.intValue());
                    atomicInt.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (atomicInt.intValue() <= 100) {
                if (atomicInt.intValue() % 2 == 0) {
                    System.out.println("偶数线程:" + atomicInt.intValue());
                    atomicInt.incrementAndGet();
                }
            }
        });

        t1.start();
        t2.start();
        System.out.println("111");
    }
}
