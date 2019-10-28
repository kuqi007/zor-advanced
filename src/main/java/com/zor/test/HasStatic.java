package com.zor.test;

public class HasStatic {
    private static int x = 100;

    public static void main(String args[]) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);

        Integer od= 3;
    }

    void waitForSignal() throws InterruptedException {
        Object obj = new Object();
        synchronized(Thread.currentThread())
        {
            obj.wait();
            obj.notify();
        }
    }
}