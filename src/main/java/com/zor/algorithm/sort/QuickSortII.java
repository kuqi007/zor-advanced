package com.zor.algorithm.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/2/1
 */
public class QuickSortII {
    public static void main(String[] args) {
        int[] array = {5, 9, 1, 9, 5, 3, 7, 6, 1};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            int key = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j && src[j] > key) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                while (i < j && src[j] < key) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            quickSort(src, begin, i - 1);
            quickSort(src, i + 1, end);
        }

    }
}
