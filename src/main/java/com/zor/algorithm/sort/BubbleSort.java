package com.zor.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arrays = new int[]{9, 3, 10, 1, 8};
        sort(arrays);
    }

    private static void sort(int[] arrays) {
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

}
