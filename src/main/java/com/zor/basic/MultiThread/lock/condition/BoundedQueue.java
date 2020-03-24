package com.zor.basic.MultiThread.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {
    private Object[] items;
    // 添加的下标，删除的下标和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    // 添加一个元素，如果数组满，则添加线程进入等待状态，直到有“空位”
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[addIndex] = t;
            // 这里思考一个问题 为什么当putIndex索引大小等于数组长度时，需要将putIndex重新设置为0？
            // 这里涉及到队列的复用，如果不设置为0，那么继续添加的话index会超出数组范围，
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            System.out.println("addIndex-" + addIndex);
            ++count;
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    // 由头部删除一个元素，如果数组为空，则删除线程进入等待状态，直到有新添加元素
    @SuppressWarnings("unchecked")
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T x = (T) items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            System.out.println("removeIndex-" + removeIndex);
            --count;
            notFull.signal();
            return x;

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedQueue<Integer> boundedQueue = new BoundedQueue<>(5);
        boundedQueue.add(1);
        boundedQueue.add(2);
        boundedQueue.add(3);
        boundedQueue.add(4);
        boundedQueue.add(5);
        boundedQueue.remove();
        boundedQueue.remove();
        boundedQueue.remove();
        boundedQueue.remove();
        boundedQueue.remove();

        boundedQueue.add(6);

    }
}
