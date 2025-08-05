package com.zor.basic.multithread.threadsafe.synchlock;


/**
 * Created by kuqi0 on 2022/6/7
 */
public class SyncTest {
    public synchronized void a() throws InterruptedException {
        System.out.println("a");
        Thread.sleep(2000);
    }

    public static synchronized void b() throws InterruptedException {
        System.out.println("b");
        Thread.sleep(2000);
    }

    public static void main(String[] args) {

        new Thread(() -> {
            SyncTest syncTest = new SyncTest();
            try {
                syncTest.a();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                b();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
