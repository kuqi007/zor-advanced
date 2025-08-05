package com.zor.basic.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);

        try {
            System.out.println("poll queue");
            blockingQueue.poll(5, TimeUnit.SECONDS);
            System.out.println("success poll queue");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
