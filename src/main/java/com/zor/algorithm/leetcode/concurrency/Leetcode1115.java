package com.zor.algorithm.leetcode.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1115. 交替打印FooBar
 * 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 * for (int i = 0; i < n; i++) {
 * print("foo");
 * }
 * }
 * <p>
 * public void bar() {
 * for (int i = 0; i < n; i++) {
 * print("bar");
 * }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 * Created by kuqi0 on 2021/6/14
 */
public class Leetcode1115 {

    class FooBar6 {
        private int n;

        public FooBar6(int n) {
            this.n = n;
        }

        private Semaphore foo = new Semaphore(1);
        private Semaphore bar = new Semaphore(0);

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                System.out.println("foo函数中foo当前可用数量" + foo.availablePermits());
                foo.acquire();
                printFoo.run();
                bar.release();
                System.out.println("foo函数中bar当前可用数量" + bar.availablePermits());
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                System.out.println("bar函数中bar当前可用数量" + bar.availablePermits());
                bar.acquire();
                printBar.run();
                foo.release();
                System.out.println("bar函数中foo当前可用数量" + foo.availablePermits());
            }
        }
    }

    /**
     * synchronized + 标志位 + 唤醒
     */
    class FooBar5 {
        private int n;

        public FooBar5(int n) {
            this.n = n;
        }

        final Object lock = new Object();
        boolean flag = true;

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (!flag) {
                        lock.wait();
                    }
                    printFoo.run();
                    flag = false;
                    lock.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                synchronized (lock) {
                    while (flag) {
                        lock.wait();
                    }
                    printBar.run();
                    flag = true;
                    lock.notify();
                }

            }
        }
    }

    /**
     * 可重入锁 + Condition
     */
    class FooBar4 {
        private int n;

        public FooBar4(int n) {
            this.n = n;
        }

        Lock lock = new ReentrantLock(true);
        private final Condition foo = lock.newCondition();
        volatile boolean flag = true;


        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                lock.lock();
                try {
                    while (!flag) {
                        foo.await();
                    }
                    printFoo.run();
                    flag = false;
                    foo.signal();
                } finally {
                    lock.unlock();
                }

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                lock.lock();
                try {
                    while (flag) {
                        foo.wait();
                    }
                    printBar.run();
                    flag = true;
                    foo.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    /**
     * CyclicBarrier 控制先后
     */
    class FooBar3 {
        private int n;

        public FooBar3(int n) {
            this.n = n;
        }

        private CyclicBarrier cb = new CyclicBarrier(2);
        private volatile boolean fin = true;

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!fin) ;
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fin = false;
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                try {
                    cb.await();
                } catch (BrokenBarrierException e) {

                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fin = true;
            }
        }
    }

    /**
     * 阻塞队列
     */
    class FooBar2 {
        private int n;
        private BlockingQueue<Integer> fooQueue;
        private BlockingQueue<Integer> barQueue;

        public FooBar2(int n) {
            this.n = n;
            fooQueue = new LinkedBlockingDeque<>(1);
            fooQueue.offer(0);
            barQueue = new LinkedBlockingDeque<>(1);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                fooQueue.take();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barQueue.put(i);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                barQueue.take();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooQueue.put(i);
            }
        }
    }

    /**
     * 自旋
     */
    class FooBar1 {
        private int n;
        private AtomicInteger curFooCounter = new AtomicInteger(-1);
        private AtomicInteger curBarCounter = new AtomicInteger(0);

        public FooBar1(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (curBarCounter.get() < i) {
                    Thread.yield();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                curFooCounter.set(i);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (curFooCounter.get() < i) {
                    Thread.yield();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                curBarCounter.set(i + 1);
            }
        }
    }
}
