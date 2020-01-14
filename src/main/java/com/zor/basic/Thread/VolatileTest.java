package com.zor.basic.Thread;

/**
 * Created by zqq on 2020/1/14.
 */
public class VolatileTest {
    private /*volatile*/ static boolean fuck = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (VolatileTest.fuck) {
                    i++;
                    System.out.println(i);
                }
                System.out.println("i am inner run" + i);
            }
        }).start();
        Thread.sleep(3000);
        VolatileTest.fuck = false;
        System.out.println("over");
    }
}
