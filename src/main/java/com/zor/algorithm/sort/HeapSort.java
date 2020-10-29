package com.zor.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * TODO 堆排序
 * Created by zqq on 2020/3/18.
 */
public class HeapSort {
    public static void main(String[] args) {

        int[] arrays = new int[]{9, 55555, 3, 10, 1, 8, 40, 69, 2222};

        heapSort(arrays);




    }

    private static void heapSort(int[] nums) {

        for (int i = nums.length - 1; i >= 0; i--) {

            maxHeap(nums, 0, i);

            swap(nums, 0, i);

        }

        System.out.println(Arrays.toString(nums));


    }


    private static void maxHeap(int[] heap, int start, int end) {
        if (start > end) {
            return;
        }
        // 我们假设下标从0开始
        int parent = start;
        int childLeft = start * 2 + 1;
        int childRight = childLeft + 1;

        if (childLeft <= end) {
            maxHeap(heap, childLeft, end);

            if (heap[childLeft] > heap[parent]) {
                swap(heap, parent, childLeft);
            }
        }

        if (childRight <= end) {
            maxHeap(heap, childRight, end);

            if (heap[childRight] > heap[parent]) {
                swap(heap, parent, childRight);
            }
        }


    }


    private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }

}
