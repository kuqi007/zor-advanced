package com.zor.algorithm.leetcode;

/**
 * @author zqq
 * @date 2021/3/1
 */
public class Leetcode303 {
    static class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            int n = nums.length;
            preSum = new int[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return preSum[j + 1] - preSum[i];
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        int i = numArray.sumRange(0, 2);
        int i1 = numArray.sumRange(2, 5);
        int i2 = numArray.sumRange(0, 5);
    }
}
