package com.zor.basic.multithread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6 * 1000);
            } else {
                System.out.println(Thread.currentThread().getName() + " get Lock Failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 查询当前线程是否保持此锁。
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName() + " release lock");
                lock.unlock();
            }
        }
    }

    /**
     * 在本例中，由于占用锁的线程会持有锁长达6秒，故另一个线程无法再5秒的等待时间内获得锁，因此请求锁会失败。
     */
    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock, "线程1");
        Thread t2 = new Thread(timeLock, "线程2");
        t1.start();
        t2.start();
    }
}
