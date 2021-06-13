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
        int[] nums2 = {3, 5, 4, 1, 2, 6};
        bubbleSort.bubbleSort(nums, nums.length);
        bubbleSort.bubbleSort2(nums2, nums.length);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 冒泡排序，a表示数组，n表示数组大小
     */
    public void bubbleSort(int[] a, int n) {
        if (n <= 1) return;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            System.out.println("sortBorder：" + (n - i - 1));
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {// 交换
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;// 表示有数据交换
                }
                cnt++;
            }
            if (!flag) {
                // 没有数据交换，提前退出
                break;
            }
        }
        System.out.println("普通冒泡比较次数: " + cnt);
    }

    /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较.
     */
    public void bubbleSort2(int[] a, int n) {
        if (n <= 1) return;
        // 最后一次交换的位置
        int lastExchange = 0;
        // 无序数据的边界，每次只需要比较这里即可退出
        int sortBorder = n - 1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // 提前退出标志位
            boolean flag = false;
            System.out.println("sortBorder：" + sortBorder);
            for (int j = 0; j < sortBorder; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    flag = true;
                    // 更新最后一次交换的位置
                    lastExchange = j;
                }
                cnt++;
            }
            sortBorder = lastExchange;
            // 没有数据交换，提前退出
            if (!flag) break;
        }
        System.out.println("优化冒泡比较次数: " + cnt);
    }

    public void bubbleSort3(int[] a, int n) {
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
