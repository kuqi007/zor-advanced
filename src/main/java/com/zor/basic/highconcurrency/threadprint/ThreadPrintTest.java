package com.zor.basic.highconcurrency.threadprint;

/**
 * 线程A每次处理只能输出一个A
 * 线程B每次输出只能输出一个B
 * 主线程不能输出A或B
 * 请尽可能不使用封装好的工具方法或类来调度线程，然后输出以下内容：
 * ABBBBABBBBABBBBABBBB….
 * 加分项：增加线程B1，行为和B一样，要求B和B1负载均衡
 *
 */
public class ThreadPrintTest {
    // 从0递增的整数用来判断打印A还是B
    private static int index = 0;
    // 设置一个打印上限，最多打印200个字符，防止打印过多CPU扛不住
    private static final int max = 200;
    // 对象锁（工具人）
    private static final Object lock = new Object();
    // 负载标志位，0的时候B线程执行，1的时候B1执行
    private static int loadFlag = 0;

    public static void main(String[] args) {
        Thread tA = new Thread(() -> {
            // 当index小于最大值max时一直循环
            while (index <= max) {
                // 争夺锁
                synchronized (lock) {
                    // 被5整除输出A
                    if (index % 5 == 0) {
                        System.out.print("A");
                        // index加一
                        index++;
                        // 唤醒其他线程
                        lock.notifyAll();
                    } else {
                        // 因为不能被5整除则释放锁，等待其他线程唤醒
                        try {
                            lock.wait();
                            // 如果被唤醒的话继续下次循环，此时获得锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });
        Thread tB = new Thread(() -> {
            while (index <= max) {

                synchronized (lock) {
                    // 不能被5整除输出B
                    if (index % 5 != 0 && loadFlag == 0) {
                        // 为了看出负载均衡效果，这里打印B0
                        System.out.print("B0");
                        index++;
                        // 标志位设置为1，下次B1线程执行
                        loadFlag = 1;
                        lock.notifyAll();
                    } else {
                        // 释放锁，等待其他线程唤醒
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        // 新建线程B1
        Thread tB1 = new Thread(() -> {
            while (index <= max) {
                synchronized (lock) {
                    // 如果不能被5整除且标志位是1则执行
                    if (index % 5 != 0 && loadFlag == 1) {
                        // 为了看出负载均衡效果，这里打印B1
                        System.out.print("B1");
                        index++;
                        // 标志位设置为0，下次B线程执行
                        loadFlag = 0;
                        lock.notifyAll();
                    } else {
                        // 释放锁，等待其他线程唤醒
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        tA.start();
        tB.start();
        tB1.start();
    }
}

