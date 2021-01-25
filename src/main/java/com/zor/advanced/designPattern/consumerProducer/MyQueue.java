package com.zor.advanced.designPattern.consumerProducer;

import java.util.LinkedList;

public class MyQueue {

    private final LinkedList<Object> list = new LinkedList<>();

    private static final Integer MAX = 5;

    private static final Integer MIN = 0;

    private final Object obj = new Object();

    public void put(Object object) {
        synchronized (obj) {            //判断list里面是否超过最大元素个数限制
            while (list.size() == MAX) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //往LinkedList添加元素
            list.add(object);
            System.out.println("element " + object + " added");            //唤醒消费者消费
            obj.notify(); //notify需在synchronize方法块内才可使用
        }
    }

    public Object get() {
        Object temp = null;
        synchronized (obj) {            //判断list是否有元素
            while (list.size() == MIN) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //移除元素
            temp = list.removeFirst();
            System.out.println("element " + temp + " consumer");
            obj.notify();
        }
        return temp;
    }


    public static void main(String[] args) {
        final MyQueue t = new MyQueue();

        new Thread(() -> {
            t.put("a");
            t.put("b");
            t.put("c");
            t.put("d");
        }, "t1").start();


        new Thread(() -> {
            try {
                t.get();
                Thread.sleep(1000);
                t.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}