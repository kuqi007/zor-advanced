package com.zor.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arrays = new int[]{9, 8, 7, 6, 5};
        //sort1(arrays);
        sort2(arrays);
    }

    private static void sort1(int[] arrays) {
        int temp;
        boolean isChange = true;
        // 外层循环是排序的趟数，需要n-1趟排序
        for (int i = 0; i < arrays.length - 1 && isChange; i++) {
            // 每比较一趟就重新初始化为false
            isChange = false;
            // 内存循环是当前趟数需要比较的次数
            for (int j = 0; j < arrays.length - i - 1; j++) {
                if (arrays[j] > arrays[j + 1]) {
                    temp = arrays[j];
                    arrays[j] = arrays[j + 1];
                    arrays[j + 1] = temp;
                    isChange = true;
                    System.out.println(Arrays.toString(arrays));
                }
            }
        }
    }

    private static void sort2(int[] arrays) {
        int temp;
        int pos, scan, num = 0;
        boolean isChange = true;
        int n = arrays.length;
        // 外层循环是排序的趟数，需要n-1趟排序
        for (pos = n - 1; pos > 0 && isChange; pos--) {
            // 每比较一趟就重新初始化为false
            isChange = false;
            num++;
            // 内存循环是当前趟数需要比较的次数，n个数需要比较n-1次
            for (scan = 0; scan < pos; scan++) {
                if (arrays[scan] > arrays[scan + 1]) {
                    temp = arrays[scan];
                    arrays[scan] = arrays[scan + 1];
                    arrays[scan + 1] = temp;
                    isChange = true;
                    System.out.println(Arrays.toString(arrays));
                }
            }
        }
        System.out.println("趟数：" + num);
    }

}
