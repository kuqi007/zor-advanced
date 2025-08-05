package com.zor.basic.multithread.threadsafe.synchlock;

/**
 * 同步代码块，锁住的是lock这个对象
 */
public class SyncObject {

    private static final Object lock = new Object();

    public void test1() {
        System.out.println("We testing 111");
        synchronized (lock) {// 锁状态写入lock对象的mark word
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void test2() {
        // 因为lock可能已经被其他线程获得了，此处会阻塞等待
        synchronized (lock) {
            System.out.println("We testing 222");
        }
    }

    public static void main(String[] args) {
        SyncObject synchUse1 = new SyncObject();
        new Thread(() -> {
            synchUse1.test1();
        }).start();

        new Thread(() -> {
            synchUse1.test2();
        }).start();


    }
}
