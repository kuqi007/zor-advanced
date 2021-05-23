package com.zor.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * Created by kuqi0 on 2021/5/23
 */
public class Leetcode347 {

    public static void main(String[] args) {
        Leetcode347 leetcode347 = new Leetcode347();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] ans = leetcode347.quickSort(nums, 2);
        System.out.println(Arrays.toString(ans));
    }

    public int[] quickSort(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        int[][] arr = new int[count.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int[] tmp = arr[index];
            tmp[0] = entry.getKey();
            tmp[1] = entry.getValue();
            index++;
        }

        int kIndex = qsort(arr, 0, arr.length - 1, k);

        int[] res = new int[k];
        for (int i = 0; i <= kIndex; i++) {
            res[i] = arr[i][0];
        }
        return res;
    }

    /**
     * 快排
     */
    private int qsort(int[][] arr, int left, int right, int k) {

        int partition = randomPartition(arr, left, right);
        if (partition + 1 == k) {
            return partition;
        } else if (partition < k) {
            return qsort(arr, partition + 1, right, k);
        } else {
            return qsort(arr, left, partition - 1, k);
        }
    }


    private int randomPartition(int[][] arr, int left, int right) {
        int i = left + new Random().nextInt(right - left + 1);
        swap(arr, i, right);
        return partition(arr, left, right);
    }

    private int partition(int[][] arr, int left, int right) {
        int[] pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            // 从大到小排列
            if (arr[j][1] > pivot[1]) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;

    }

    private void swap(int[][] arr, int i, int j) {
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] heapSort(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }

        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(count::get));
        for (Integer key : count.keySet()) {
            if (queue.size() < k) {
                queue.offer(key);
            } else if (count.get(key) > count.get(queue.peek())) {
                queue.poll();
                queue.add(key);
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll();
        }
        return ans;
    }
}
