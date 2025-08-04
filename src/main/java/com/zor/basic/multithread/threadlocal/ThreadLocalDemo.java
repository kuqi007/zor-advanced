package com.zor.basic.multithread.threadlocal;

/**
 * Created by zqq on 2020/3/2.
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> localVar = new InheritableThreadLocal<>();
        localVar.set("aaa");
        new Thread(() -> {
            String s = localVar.get();
            System.out.println("子线程InheritableThreadLocal " + s);
        }).start();
    }
}
