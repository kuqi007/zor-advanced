package com.zor.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by kuqi0 on 2021/5/4
 */
public class RandomQuickSort {
    public static void main(String[] args) {
        RandomQuickSort leetcode912 = new RandomQuickSort();
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
