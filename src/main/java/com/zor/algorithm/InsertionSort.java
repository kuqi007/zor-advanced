package com.zor.algorithm;


import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arrays = new int[]{9, 3, 10, 1, 8};
        sort(arrays);
    }

    private static void sort(int[] arrays) {

        for (int i = 1, j, temp; i < arrays.length; i++) {
            temp = arrays[i];

            for (j = i - 1; j >= 0 && arrays[j] > temp; j--) {
                arrays[j + 1] = arrays[j];
            }

            arrays[j + 1] = temp;
            System.out.println(Arrays.toString(arrays));
        }

    }
}
