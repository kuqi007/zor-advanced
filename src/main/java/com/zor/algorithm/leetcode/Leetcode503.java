package com.zor.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 * <p>
 * Created by kuqi007 on 2021/3/6
 */
public class Leetcode503 {

    public static void main(String[] args) {

        int[] nums = {5, 4, 3, 2, 1};
        int[] ints = nextGreaterElements(nums);
        System.out.println("单调栈解法答案：" + Arrays.toString(ints));
        int[] ints1 = solution1(nums);
        System.out.println("暴力解法答案：" + Arrays.toString(ints1));

    }

    /**
     * 单调栈
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        // 遍历两遍数组
        for (int i = 0; i < 2 * n - 1; i++) {
            // nums[i%n]模拟循环数组
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }

    /**
     * 看起来简洁的暴力解法
     */
    public static int[] solution1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = i + 1; j < n + i; j++) {
                // 模拟循环数组
                if (nums[i] < nums[j % n]) {
                    res[i] = nums[j % n];
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 愚蠢的暴力解法
     */
    public static int[] solution2(int[] nums) {
        int[] res = new int[nums.length];
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int num = nums[i];
            int j = i + 1;
            boolean found = false;
            while (j < n && !found) {
                if (num < nums[j]) {
                    res[i] = nums[j];
                    found = true;
                }
                j++;
            }

            if (i > 0) {
                int k = 0;
                while (k < i && !found) {
                    if (num < nums[k]) {
                        res[i] = nums[k];
                        found = true;
                    }
                    k++;
                }
            }
            if (!found) {
                res[i] = -1;
            }
            i++;
        }
        return res;
    }
}
