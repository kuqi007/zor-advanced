package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * 选择排序
 * Created by kuqi0 on 2021/6/13
 */
public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        int[] nums = {4, 5, 6, 1, 3, 2};
        selectionSort.selectionSort(nums, nums.length);
        System.out.println(Arrays.toString(nums));
    }

    // 选择排序，a表示数组，n表示数组大小
    private void selectionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) {
            // 查找最小值
            int minIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }
}
