package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/6/13
 */
public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] nums = {4, 5, 6, 1, 3,2};
        insertionSort.insertionSort(nums, nums.length);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 插入排序的精髓就是先通过比较找到要插入的位置，再插入；
     * 比较过程则是在已插入的数据中进行比较。a[i]代表本次要插入的数，a[j]代表已插入的数，j=i-1代表要比较的次数，
     * 第一次直接插入，第二次比较1个已插入数据，因为已插入数据都已经排好序，所以遇到小于自己的数直接跳出比较进行插入即可。
     */
    // 插入排序，a表示数组，n表示数组大小
    private void insertionSort(int[] a, int n) {
        if (n <= 1) return;

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j + 1] = a[j];// 数据移动
                } else {
                    break;
                }
            }
            a[j + 1] = value;// 插入数据
        }
    }
}
