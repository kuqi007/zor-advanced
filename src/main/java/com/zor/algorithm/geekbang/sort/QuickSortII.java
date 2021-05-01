package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/5/1
 */
public class QuickSortII {
    public static void main(String[] args) {

        QuickSortII quickSortII = new QuickSortII();
        int[] arr = {8, 1, 4, 6, 9, -1};
        quickSortII.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr, int left, int right) {
        // 退出条件
        if (left >= right) return;

        int partition = partition(arr, left, right);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    /**
     * 快排的关键是就是分区函数
     * https://cdn.jsdelivr.net/gh/kuqi007/picGo/202120210427201802.jpeg
     */
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
