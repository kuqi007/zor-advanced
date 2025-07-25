package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * 169. 多数元素
 * <p>
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Leetcode169 {
    public static void main(String[] args) {
        Leetcode169 leetcode169 = new Leetcode169();
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        int r = leetcode169.solution2(nums);
        System.out.println(r);
    }

    /**
     * 摩尔投票
     */
    public int solution2(int[] nums) {
        // 摩尔排序的核心就是2个变量，一个candidate，一个计数器count
        // count归0，重新选举
        int candidate = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }


    /**
     * 直接排序，下标从0开始，所以n/2个元素必是多数元素
     * 时间复杂度为O(nlogn)
     */
    public int solution1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
