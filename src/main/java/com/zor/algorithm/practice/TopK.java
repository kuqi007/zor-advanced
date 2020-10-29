package com.zor.algorithm.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zqq
 * @date 2020/10/29
 */
public class TopK {

    public static void main(String[] args) {
        // TopK问题
        int[] arrays = new int[]{91, 55555, 3, 10, 1, 8, 40, 69, 2222};
        solutionByHeap(arrays, 5);
    }

    /**
     * 利用堆排序 取最大的TopK
     *
     * @param input 数组
     * @param k     前K
     */
    public static void solutionByHeap(int[] input, int k) {
        List<Integer> list = new ArrayList<>();
        if (k < 0 || k > input.length || k == 0) {
            System.out.println("K值不合法");
            return;
        }
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : input) {
            // 先把数压入队列
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() < num) {
                // 当队列的数量超过K的时候，如果进来的数大于队列头部的数，则剔除掉头部的数，将进来的数继续压入队列
                queue.poll();
                queue.add(num);
            }
        }
        while (k-- > 0) {
            list.add(queue.poll());
        }

        System.out.println(list.toString());
    }


}
