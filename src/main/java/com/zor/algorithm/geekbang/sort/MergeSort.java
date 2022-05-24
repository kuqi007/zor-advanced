package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/5/4
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {4, 5, -1, 7, 9, 1, -11};

        mergeSort.mergeSort(arr, arr.length);

        System.out.println(Arrays.toString(arr));


    }

    public void merge_sort(int[] A, int n) {
        merge_sort_c(A, 0, n - 1);
    }

    public void merge_sort_c(int[] A, int p, int r) {

        if (p >= r) return;
        int q = (p + r) / 2;
        merge_sort_c(A, p, q);
        merge_sort_c(A, q + 1, r);

        merge_(A, p, q, r);
    }

    public void merge_(int[] A, int p, int q, int r) {

        int i = p, j = q + 1, k = 0;

        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[k++] = A[i++];
            } else {
                tmp[k++] = A[j++];
            }
        }
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            tmp[k++] = A[start++];
        }
        for (i = 0; i <= r - p; i++) {
            A[p + i] = tmp[i];
        }


    }

    // 归并排序算法，A是数组，n表示数字大小
    public void mergeSort(int[] A, int n) {
        mergeSortC(A, 0, n - 1);
    }

    // 递归调用函数
    public void mergeSortC(int[] A, int p, int r) {
        if (p >= r) return;

        // 取p到r之间的中间位置
        int q = p + (r - p) / 2;

        // 分治递归
        mergeSortC(A, p, q);
        mergeSortC(A, q + 1, r);
        // 将A[p...q]和[q+1...r]合并为A[p...r]
        merge1(A, p, q, r);
    }

    private void merge(int[] A, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;// 初始化变量i,j,k
        int[] tmp = new int[r - p + 1];// 申请一个大小跟A一样的临时数组
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[k++] = A[i++];// i++等于i=i+1
            } else {
                tmp[k++] = A[j++];
            }
        }
        // 判断哪个子数组中有剩余的数据
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = A[start++];
        }
        // 将tmp中的数组拷贝回A
        for (i = 0; i <= r - p; i++) {
            A[p + i] = tmp[i];
        }
    }

    /**
     * 合并（哨兵）
     */
    private static void mergeBySentry(int[] arr, int p, int q, int r) {
        int[] leftArr = new int[q - p + 2];
        int[] rightArr = new int[r - q + 2];

        for (int i = 0; i <= q - p; i++) {
            leftArr[i] = arr[p + i];
        }
        // 第一个数组添加哨兵（最大值）
        leftArr[q - p + 1] = Integer.MAX_VALUE;

        for (int i = 0; i < r - q; i++) {
            rightArr[i] = arr[q + 1 + i];
        }
        rightArr[r - q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;
        while (k <= r) {
            // 当左边数组到达哨兵值时，i不再增加，直到右边数组读取完剩余值，同理右边数组也一样
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }


    /**
     * TODO 这样写不是更简单？
     */
    private void merge1(int[] A, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tempArr = new int[r - p + 1];
        while (i <= q || j <= r) {
            if (i > q || (j <= r && A[i] > A[j])) {
                tempArr[k++] = A[j++];
            } else {
                tempArr[k++] = A[i++];
            }
        }

        for (i = 0; i <= r - p; i++) {
            A[p + i] = tempArr[i];
        }
    }


}
