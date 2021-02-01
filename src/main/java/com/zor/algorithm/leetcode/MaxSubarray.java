package com.zor.algorithm.leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 *
 * @author zqq
 * @date 2021/1/27
 */
public class MaxSubarray {

    public static void main(String[] args) {

    }

    /**
     * TODO 有点懵
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        // ans是结果
        int ans = nums[0];
        // sum是最大序列之和
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum = +num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}
