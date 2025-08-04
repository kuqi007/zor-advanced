package com.zor.basic.multithread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印数组
 *
 * @author zqq
 * @date 2021/1/25
 */
public class PrintArray {

    private boolean flag = true;

    //全局锁
    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        int[] b = new int[]{4, 5, 6};

        PrintArray printArray = new PrintArray();

        new Thread(() -> printArray.printA(a)).start();

        new Thread(() -> printArray.printB(b)).start();

    }

    private void printA(int[] arr) {
        lock.lock();
        try {
            for (int i : arr) {
                if (!flag) {
                    condition.await();
                }

                System.out.println(i);
                flag = !flag;
                condition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printB(int[] arr) {
        lock.lock();
        try {
            for (int i : arr) {
                if (flag) {
                    condition.await();
                }
                System.out.println(i);
                flag = !flag;
                condition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
