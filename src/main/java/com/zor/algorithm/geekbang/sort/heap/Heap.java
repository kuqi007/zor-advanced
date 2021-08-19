package com.zor.algorithm.geekbang.sort.heap;

import static reactor.core.Disposables.swap;

/**
 * 构建堆
 * Created by kuqi0 on 2021/8/16
 */
public class Heap {
    private int[] a; // 数组，从下标1开始存储数据
    private int n; // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {// 自下往上堆化
            swap(a, i, i / 2);// swap()函数作用：交换下标为i和i/2的两个元素
            i = i / 2;// 自下往上
        }
    }


    public void removeMax() {
        if (count == 0) return; // 堆中没有数据
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    private void heapify(int[] a, int n, int i) { // 自上往下堆化
        while (true) {
            int maxPos = i;
            // 跟左子节点比较，如果比左子节点小，则maxPos等于左子节点
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            // 此时再拿maxPos跟右子节点比较，如果比右子节点小，此时maxPos等于右子节点，因为要满足每个节点的值都大于等于（或小于等于）其子树节点的值
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

}
