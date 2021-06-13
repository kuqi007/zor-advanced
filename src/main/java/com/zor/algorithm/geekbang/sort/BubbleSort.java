package com.zor.algorithm.geekbang.sort;


import java.util.Arrays;

/**
 * 冒泡排序
 * Created by kuqi0 on 2021/6/13
 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] nums = {3, 5, 4, 1, 2, 6};
        bubbleSort.bubbleSort2(nums, nums.length);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 冒泡排序，a表示数组，n表示数组大小
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {// 交换
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;// 表示有数据交换
                }
            }
            if (!flag) {
                // 没有数据交换，提前退出
                break;
            }
        }
    }

    public void bubbleSort2(int[] a, int n) {
        if (n <= 1) return;
        // 如果已经排好序，就无须继续交换
        boolean flag = true;
        for (int i = 0; flag && i < n; i++) {
            flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;
                }
            }
        }

    }
}
