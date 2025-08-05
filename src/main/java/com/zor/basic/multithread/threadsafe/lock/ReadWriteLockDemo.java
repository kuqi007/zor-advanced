package com.zor.basic.multithread.threadsafe.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhuqiqi03
 * @date 2021/6/9
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {
        // TODO: 2021/6/9
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock lock = readWriteLock.readLock();

        try {
            lock.lock();
        } finally {
            lock.unlock();
        }


    }


}
