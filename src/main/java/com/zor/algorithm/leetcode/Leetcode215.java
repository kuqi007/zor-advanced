package com.zor.algorithm.leetcode;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * Created by kuqi0 on 2021/5/4
 */
public class Leetcode215 {

    public static void main(String[] args) {
        Leetcode215 leetcode215 = new Leetcode215();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int sort = leetcode215.findKthLargest(nums, 2);
        System.out.println(sort);

    }

    public int findKthLargest(int[] nums, int k) {
        //return nums[quickSort(nums, 0, nums.length - 1, k)];

        // 堆排序
        // 默认最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Integer num : nums) {
            if (heap.size() < k) {
                heap.add(num);
            } else if (num > heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }
        return heap.peek();

    }


    /**
     * 快排变体
     */
    private int quickSort(int[] arr, int left, int right, int k) {
        int partition = partition(arr, left, right);
        if (partition + 1 == k) {
            return partition;
        } else if (partition + 1 < k) {
            return quickSort(arr, partition + 1, right, k);
        } else {
            return quickSort(arr, left, partition - 1, k);
        }
    }

    private int randomPartition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }


    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            // 从大到小排序
            if (arr[j] > pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
