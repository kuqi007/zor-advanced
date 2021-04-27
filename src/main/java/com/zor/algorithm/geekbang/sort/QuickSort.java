package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/4/27
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {5, 1, 8, 0, 2, 4};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    public void quickSort(int[] A, int p, int r) {
        if (p >= r) return;

        int q = partition(A, p, r);
        quickSort(A, 0, q - 1);
        quickSort(A, q + 1, r);
    }


    /**
     * 分区函数
     *
     * @param A 数组
     * @param p left
     * @param r right
     */
    private int partition(int[] A, int p, int r) {
        int pivot = A[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (A[j] < pivot) {
                // 交换j和i位置的数，这样i位置的数肯定比pivot小，遍历完之后，i左边的数都比pivot小
                swap(A, i, j);
                i++;
            }
        }
        // 然后讲pivot位置的数与i交换，这样罪之后，pivot左边的数均比它小，右边数均比它大
        swap(A, i, r);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
