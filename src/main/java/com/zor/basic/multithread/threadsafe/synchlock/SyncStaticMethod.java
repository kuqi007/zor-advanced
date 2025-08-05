package com.zor.basic.multithread.threadsafe.synchlock;

/**
 * 测试加在静态方法上，实际上锁的是当前方法所属的Class对象
 */
public class SyncStaticMethod {
    public synchronized static void test1() {
        System.out.println("We testing 111");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }



    public synchronized static void test2() {
        System.out.println("We testing 222");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            test1();// 锁的Class对象，所有属于这个类的方法，都必须等待
        }).start();

        new Thread(() -> {
            test2();//当前类对象已被thread1获取到锁，那么肯定是要等待的
        }).start();
    }
}
