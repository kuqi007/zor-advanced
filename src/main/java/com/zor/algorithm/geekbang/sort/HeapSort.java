package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2021/8/16
 */
public class HeapSort {

    public static void main(String[] args) {
        // index=0的不使用
        int[] arr = {-999999, 7, 1, 9, 3, -1, -10, 8};
        sort(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }


    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    private static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            // 跟左子节点比较，如果比左子节点小，则maxPos等于左子节点
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            // 此时再拿maxPos跟右子节点比较，如果比右子节点小，此时maxPos等于右子节点，因为要满足每个节点的值都大于等于（或小于等于）其子树节点的值
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = 2 * i + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
        }

    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

}
