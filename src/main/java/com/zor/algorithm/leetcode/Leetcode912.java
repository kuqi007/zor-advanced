package com.zor.algorithm.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 912. 排序数组
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * Created by kuqi0 on 2021/4/27
 */
public class Leetcode912 {
    public static void main(String[] args) {
        Leetcode912 leetcode912 = new Leetcode912();
        int[] nums = {4, 3, 2, -1};
        int[] arras = leetcode912.sortArray(nums);
        System.out.println("Arrays.toString(arras) = " + Arrays.toString(arras));
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = randomPartition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    /**
     * 随机化快排，在数组已经有序的情况下，打破有序
     */
    private int randomPartition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(arr, right, i);
        return partition(arr, left, right);
    }

    private int partition(int[] arr, int left, int right) {
        // 选定右边作为基准值
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
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
