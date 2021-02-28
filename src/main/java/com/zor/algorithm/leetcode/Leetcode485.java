package com.zor.algorithm.leetcode;

/**
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @author zqq
 * @date 2021/2/15
 */
public class Leetcode485 {
    public static void main(String[] args) {

        int count = solution2(new int[]{1, 0, 1, 1, 0, 1});
        System.out.println(count);


    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, left = 0, right = 0;
        for (; right < nums.length; right++) {
            if (nums[right] == 0) {
                count = Math.max(count, right - left);
                left = right + 1;
            }
        }
        return Math.max(count, right - left);
    }

    public static int solution2(int[] nums) {
        int count = 0, maxCount = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }

    public static int solution3(int[] nums) {
        int res = 0, left = 0, right = 0;
        while (right < nums.length) {

            if (nums[right] == 0) {
                res = Math.max(res, right - left);
                left = right + 1;
            }
            ++right;
        }
        return Math.max(res, right - left);
    }

    public static int solution4(int[] nums) {
        int n = nums.length;
        int res = 0, left = 0, right = 0;
        while (right < n) {
            if (nums[right] == 0) {
                res = Math.max(res, right - left);
                left = right + 1;
            }
            right++;
        }

        return Math.max(res, right - left);

    }
}
