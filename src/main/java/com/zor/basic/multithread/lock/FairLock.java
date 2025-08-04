package com.zor.basic.multithread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * https://www.cnblogs.com/gxyandwmm/p/9387833.html
 */
public class FairLock implements Runnable {
    //公平锁
    //public static ReentrantLock fairLock = new ReentrantLock(true);
    //非公平锁
    public static ReentrantLock fairLock = new ReentrantLock(false);

    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "，获得锁!");
            } finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "线程1");
        Thread t2 = new Thread(fairLock, "线程2");
        t1.start();
        t2.start();
    }
}
