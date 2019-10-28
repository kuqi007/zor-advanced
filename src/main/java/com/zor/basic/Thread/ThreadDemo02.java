package com.zor.basic.Thread;

/**
 * Created by zqq on 2019/5/22.
 */
class MyThread extends Thread {
    private int ticket = 5;
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("线程" + name + "卖票" + (ticket--));
            }
        }


    }
}

public class ThreadDemo02 {
    //public ThreadDemo02(){
    //    System.out.println("11");
    //}
    //
    //static {
    //    System.out.println("静态代码块");
    //}

    public static void main(String args[]) {


        MyThread A = new MyThread("A");
        //new Thread(A).start();
        MyThread B = new MyThread("B");
        //new Thread(A).start();
        A.start();
        B.start();
    }
}
