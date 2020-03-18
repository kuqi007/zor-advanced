package com.zor.algorithm;

import java.util.Arrays;

/**
 * Created by zqq on 2020/3/18.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arrays = new int[]{9, 3, 10, 1, 8};
        sort(arrays);
    }

    private static void sort(int[] arrays) {
        int pos;
        int temp;
        for (int i = 0; i < arrays.length - 1; i++) {
            pos = 0;
            for (int j = 0; j < arrays.length - i; j++) {
                if (arrays[j] > arrays[pos]) {
                    pos = j;
                }
            }
            temp = arrays[pos];
            arrays[pos] = arrays[arrays.length - 1 - i];
            arrays[arrays.length - 1 - i] = temp;
            System.out.println(Arrays.toString(arrays));
        }
    }
}
