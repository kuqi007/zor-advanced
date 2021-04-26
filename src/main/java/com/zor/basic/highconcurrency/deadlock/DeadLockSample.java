package com.zor.basic.highconcurrency.deadlock;

/**
 * * Java program to create a deadlock by imposing circular wait. * * @author
 * WINDOWS 8 *
 */
public class DeadLockSample {
    public static void main(String[] args) {
        //匿名内部类
        new Thread(new Runnable() {
            /*
             * This method request two locks, first String and then Integer
             **/
            @Override
            public void run() {
                synchronized (String.class) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "Acquired lock on String.class object");
                        //必须sleep一段时间，否则当前线程锁释放之后可能另外线程还未执行
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (Integer.class) {
                        System.out.println(Thread.currentThread().getName() + "Acquired lock on Integer.class object");
                    }
                }
            }
        }).start();


        //java8写法
        new Thread(() -> {
            /*
             * * This method also requests same two lock but in exactly * Opposite order
             * i.e. first Integer and then String. * This creates potential deadlock, if
             * one thread holds String lock * and other holds Integer lock and they wait
             * for each other, forever.
             */
            synchronized (Integer.class) {
                try {
                    System.out.println(Thread.currentThread().getName() + "Acquired lock on Integer.class object");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (String.class) {
                    System.out.println(Thread.currentThread().getName() + "Acquired lock on String.class object");
                }
            }
        }).start();
    }
}
