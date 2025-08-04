package com.zor.basic.multithread.easycoding;

/**
 * volatile不是原子性的测试
 * Created by kuqi0 on 2021/5/22
 */
public class VolatileNotAtomic {
    private static volatile long count = 0;
    private static final int NUMBER = 10000;

    public static void main(String[] args) throws InterruptedException {

        Thread subtractThread = new SubtractThread();
        subtractThread.start();

        for (int i = 0; i < NUMBER; i++) {
            // 如果不加锁的话，结果可能不为0
            synchronized (VolatileNotAtomic.class) {
                count++;
            }
        }

        // 等待减法线程结束
        //while (subtractThread.isAlive()) {
        //}
        subtractThread.join();

        System.out.println("count 最后的值为：" + count);

    }


    private static class SubtractThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                synchronized (VolatileNotAtomic.class) {
                    count--;
                }
            }
        }
    }
}
