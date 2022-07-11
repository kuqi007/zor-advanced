package com.zor.basic.highconcurrency;

/**
 * Created by kuqi0 on 2022/7/4
 */
public class InterruptDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadA());
        t1.start();
        t1.interrupt();

    }


}

class ThreadA implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("执行中，当前线程状态：" + Thread.currentThread().isInterrupted());

                Thread.sleep(100);

            } catch (InterruptedException e) {
                //    throw new RuntimeException(e);
            }
        }
    }
}