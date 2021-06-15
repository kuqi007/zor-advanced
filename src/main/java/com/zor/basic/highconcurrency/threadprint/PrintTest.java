package com.zor.basic.highconcurrency.threadprint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阿里笔试
 * 设计一个多线程打印程序，第i个线程只打印i-1数字，比如第1个线程打印数字0，第2个线程只打印数字1，依次类推。任意给定一个数字序列，比如3382019835830，能够使用该程序打印出来
 * Created by kuqi0 on 2021/5/19
 */
public class PrintTest {

    public static void main(String[] args) {
        PrintTest printTest = new PrintTest();
        printTest.lockTest("3382019835830");
    }

    private int index = 0;

    public void test(String s) {
        int n = s.length();
        Object obj = new Object();
        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            new Thread(() -> {
                while (index < n) {
                    synchronized (obj) {
                        int num = s.charAt(index) - '0';
                        if (threadNum != num) {
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.print(threadNum);
                            index++;
                            obj.notifyAll();
                        }

                    }
                }
            }).start();
        }
    }

    private int index1 = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void lockTest(String s) {
        int n = s.length();
        for (int i = 1; i <= 10; i++) {
            final int threadNum = i;
            new Thread(() -> {
                try {
                    lock.lock();
                    while (index1 < n) {
                        int num = s.charAt(index1) - '0';
                        if (threadNum - 1 != num) {
                            condition.await();
                        } else {
                            System.out.print(num);
                            index1++;
                            condition.signalAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }).start();
        }

    }

    public void test3(String numStr) {
        Object object = new Object();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                synchronized (object) {
                    while (true) {
                        try {
                            while (index < numStr.length() && (numStr.charAt(index) - 48) != finalI) {
                                // 需要输出数字与当前线程不符合，进入等待
                                //System.out.println(finalI + " wait");
                                object.wait();
                            }
                            if (index == numStr.length()) {
                                // 控制结束,遍历完成退出
                                return;
                            }
                            System.out.print(finalI);
                            // 当前下标数字已输出，唤醒所有线程，准备下一位数字输出
                            index++;
                            object.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }


}
