package com.zor.algorithm.leetcode;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1114. 按序打印
 * 我们提供了一个类：
 * <p>
 * public class Foo {
 * public void first() { print("first"); }
 * public void second() { print("second"); }
 * public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 * <p>
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * 示例 2:
 * <p>
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 * 你看到的输入格式主要是为了确保测试的全面性。
 * Created by kuqi0 on 2021/6/12
 */
public class Leetcode1114 {
    class Foo {
        AtomicBoolean firstDone=new AtomicBoolean(false);
        AtomicBoolean secondDone=new AtomicBoolean(false);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstDone.set(true);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (!firstDone.get()){

            }

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondDone.set(true);
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (!secondDone.get()){

            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }

    class Foo1 {
        private int curThreadNum = 1;
        private final Object lock = new Object();

        public Foo1() {
        }


        public void first(Runnable printFirst) throws InterruptedException {

            synchronized (lock) {
                while (curThreadNum != 1) {
                    lock.wait();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                lock.notifyAll();
                curThreadNum++;
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (lock) {
                while (curThreadNum != 2) {
                    lock.wait();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                lock.notifyAll();
                curThreadNum++;

            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            synchronized (lock) {
                while (curThreadNum != 3) {
                    lock.wait();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                lock.notifyAll();
                curThreadNum++;

            }
        }
    }
}
