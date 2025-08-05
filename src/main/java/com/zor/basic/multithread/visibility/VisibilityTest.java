package com.zor.basic.multithread.visibility;

public class VisibilityTest {
    private static volatile boolean flag = false; // 静态变量
    
    public static void main(String[] args) {
        // 线程1：修改flag
        new Thread(() -> {
            sleep(1000);
            flag = true; // 修改静态变量
            System.out.println("Flag set to true");
        }).start();
        
        // 线程2：读取flag
        new Thread(() -> {
            while (!flag) { // 可能永远看不到true
                // 空循环
            }
            System.out.println("Flag detected as true");
        }).start();
    }
    
    private static void sleep(long ms) {
        try { Thread.sleep(ms); } 
        catch (InterruptedException e) {}
    }
}