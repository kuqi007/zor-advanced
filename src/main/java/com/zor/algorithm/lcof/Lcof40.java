package com.zor.algorithm.lcof;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * Created by kuqi0 on 2021/8/11
 */
public class Lcof40 {

    public static void main(String[] args) {
        Lcof40 lcof40 = new Lcof40();
        int[] arr = {3, 2, 1};

        int[] res = lcof40.heapSort(arr, 2);
        System.out.println(Arrays.toString(res));
    }

    /**
     * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/
     * 计数排序
     */
    public int[] countingSort(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int i = 0; i < counter.length; i++) {
            while (counter[i]-- > 0 && idx < k) {
                res[idx++] = i;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }

    /**
     * 堆排序
     */
    public int[] heapSort(int[] arr, int k) {
        if (k <= 0) return new int[]{};
        // 大顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : arr) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(i);
            } else {
                if (priorityQueue.peek() > i) {
                    priorityQueue.poll();
                    priorityQueue.offer(i);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }


    public int[] solution2(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1, k);
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }


    /**
     * 快排变体
     */
    public void quickSort(int[] arr, int left, int right, int k) {
        if (left >= right) return;
        int partition = randomPartition(arr, left, right);
        int index = k - 1;
        if (partition < index) {
            quickSort(arr, partition + 1, right, k);
        } else if (partition > index) {
            quickSort(arr, left, partition - 1, k);
        }
    }

    private int randomPartition(int[] arr, int left, int right) {
        int i = new Random().nextInt(right - left + 1) + left;
        swap(arr, i, right);
        return partition(arr, left, right);
    }

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
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public int[] solution1(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }
}
