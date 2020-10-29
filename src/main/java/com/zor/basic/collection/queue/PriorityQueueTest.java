package com.zor.basic.collection.queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zqq
 * @date 2020/10/29
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        // Java中PriorityQueue通过二叉小顶堆实现

        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(19);
        queue.add(9);
        queue.add(11);
        queue.add(1111);

        System.out.println(queue.toString());

    }
}
