package com.zor.basic.multithread.threadsafe.synchlock;

/**
 * 测试加在实例方法上，实际上锁的是这个实例对象
 */
public class SyncInstanceMethod {
    public synchronized void test1() {
        System.out.println("We testing 111");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void test2() {
        System.out.println("We testing 222");
    }

    public static void main(String[] args) {
        SyncInstanceMethod synchUse1 = new SyncInstanceMethod();
        SyncInstanceMethod synchUse2 = new SyncInstanceMethod();
        new Thread(() -> {
            synchUse1.test1();// 因为synchronized同步实例方法的话，锁住的是当前实例对象
        }).start();

        new Thread(() -> {
            synchUse1.test2();//线程2此时想调用test2方法，也被阻塞了，因为他们锁的是同一个实例对象，
            //synchUse2.test2();// 如果这时候新建另一个对象synchUse2，来调用不论test1还是test2都是没问题的
        }).start();





    }
}
