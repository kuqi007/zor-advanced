package com.zor.basic.MultiThread.tools.Semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        SemaphoreDemo service = new SemaphoreDemo();
        for (int i = 0; i < 10; i++) {
            MyThread t = new MyThread("thread" + (i + 1), service);
            t.start();// 这里使用 t.run() 也可以运行，但是不是并发执行了
            System.out.println("可用通路数：" + service.availablePermits());
        }
    }

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static Semaphore semaphore = new Semaphore(6);// 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码

    public static void doSomething() {
        try {
            /*
              在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
              因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
              */
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            semaphore.release(2);
            System.out.println(" ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int availablePermits() {    // 查看可用通路数
        return semaphore.availablePermits();
    }

    public static String getFormatTimeStr() {
        return sf.format(new Date());
    }

    static class MyThread extends Thread {

        public MyThread(String name, SemaphoreDemo service) {
            super();
            this.setName(name);
        }

        @Override
        public void run() {
            doSomething();
        }
    }

}
