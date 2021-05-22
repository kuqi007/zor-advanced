package com.zor.basic.highconcurrency.threadprint;

/**
 * @author zqq
 * @date 2021/1/25
 */
public class ThreadPrintSimple {
    // 递增的整数用来判断打印A还是B
    private static int index = 0;
    // 设置一个打印上限，最多打印200个字符
    private static final int max = 200;
    // 对象锁（工具人）
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread tA = new Thread(() -> {
            // 当index小于最大值max时一直循环

            // 争夺锁
            synchronized (lock) {
                while (index <= max) {
                    if (index % 5 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print("A");
                    index++;
                    lock.notifyAll();
                }
            }

        });
        Thread tB = new Thread(() -> {

            // 争夺锁
            synchronized (lock) {
                while (index <= max) {
                    // 不能被5整除输出B
                    if (index % 5 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    index++;
                    lock.notifyAll();
                }
            }
        });
        tA.start();
        tB.start();
    }
}
