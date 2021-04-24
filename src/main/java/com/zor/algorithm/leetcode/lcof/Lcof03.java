package com.zor.algorithm.leetcode.lcof;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * Created by kuqi0 on 2021/4/24
 */
public class Lcof03 {


    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        int[] map = new int[n];
        for (int num : nums) {
            if (map[num] > 0) {
                return num;
            } else {
                map[num] = 1;
            }
        }
        return -1;
    }

    /**
     * TODO 原地置换做法。有点绕
     */
    public int solution0(int[] nums) {
        int i = 0;
        // 原地置换
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

}
