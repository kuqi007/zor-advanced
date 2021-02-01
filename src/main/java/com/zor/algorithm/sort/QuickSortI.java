package com.zor.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序第一种写法
 * Created by zqq on 2020/3/18.
 */
public class QuickSortI {
    public static void main(String[] args) {
        int[] arrays = new int[]{4, 3, 2, -1};
        QuickSortI quickSort = new QuickSortI();

        int[] sort = quickSort.sort(arrays);
        System.out.println(Arrays.toString(sort));
    }


    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }


    private int partition(int[] arr, int left, int right) {
        // 设定基准值
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                System.out.println("交换之后数组："+Arrays.toString(arr));
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
