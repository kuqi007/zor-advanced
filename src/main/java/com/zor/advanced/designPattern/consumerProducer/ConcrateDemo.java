package com.zor.advanced.designPattern.consumerProducer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现生产消费者的例子
 * 要求：
 * 有两股力量：生产和消费
 * 当仓库生产满了的时候就要通知消费者进行消费，并且停止生产
 * 当仓库空的时候，消费者要通知生产者进行生产，并且停止消费
 * 其它情况，正常生产、消费。
 * <p>
 * 生产者与消费者模型中，要保证以下几点：
 * 1 同一时间内只能有一个生产者生产
 * 2 同一时间内只能有一个消费者消费
 * 3 共享空间空时消费者不能继续消费
 * 4 共享空间满时生产者不能继续生产
 *
 * @author mr_dsw
 */
public class ConcrateDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        ProduceThread produceThread = new ProduceThread(resource);
        ConsumeThread consumeThread = new ConsumeThread(resource);
        //四个生产者
        new Thread(produceThread).start();
        new Thread(produceThread).start();
        new Thread(produceThread).start();
        new Thread(produceThread).start();
        //四个消费者
        new Thread(consumeThread).start();
        new Thread(consumeThread).start();
        new Thread(consumeThread).start();
        new Thread(consumeThread).start();
    }
}

class Resource {
    private final int MAX_SIZE = 10;
    private LinkedList<Object> list = new LinkedList<Object>();
    private Lock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    /**
     * 生产物品，存在多个生产者
     */
    public void produce() {
        //如果生产满了，则就唤醒消费者
        lock.lock();
        while (list.size() == MAX_SIZE) {
            System.out.println("生产满了，暂时无法生产：" + list.size());
            emptyCondition.signal();
            try {
                fullCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        System.out.println(Thread.currentThread().getName() + "生产新产品，共有：" + list.size());
        lock.unlock();
    }

    /**
     * 消费者，存在多个消费者
     */
    public void consume() {
        lock.lock();
        while (list.size() == 0) {
            System.out.println("没有物品了，需要通知生产了");
            fullCondition.signal();
            try {
                emptyCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "消费产品，共有：" + list.size());
        list.remove();
        lock.unlock();
    }
}

class ProduceThread implements Runnable {
    private Resource resource;

    public ProduceThread(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        for (; ; )
            resource.produce();
    }
}

class ConsumeThread implements Runnable {
    private Resource resource;

    public ConsumeThread(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        for (; ; )
            resource.consume();
    }
}