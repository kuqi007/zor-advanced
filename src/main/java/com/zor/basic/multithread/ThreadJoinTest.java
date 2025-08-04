package com.zor.basic.multithread;

/**
 * Created by kuqi0 on 2021/5/22
 */
public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {


        Thread thread1 = new Thread(() -> {
            System.out.println("线程1运行-------");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();


        Thread thread2 = new Thread(() -> {
            System.out.println("线程2运行-------");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();

        System.out.println("主线程等待子线程执行=====");
        long l = System.currentTimeMillis();

        thread1.join();
        thread2.join();
        long l1 = System.currentTimeMillis();
        System.out.println("等待结束，耗时：" + (l1 - l) + "ms");


    }

}
