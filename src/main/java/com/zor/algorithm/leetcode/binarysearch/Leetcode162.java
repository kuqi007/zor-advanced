package com.zor.algorithm.leetcode.binarysearch;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p>
 * <p>
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
 *
 * @author zhuqiqi03
 * @date 2021/6/15
 */
public class Leetcode162 {
    public static void main(String[] args) {
        Leetcode162 leetcode162 = new Leetcode162();
        int[] nums = {1,2,1,3,5,6,4};
        int idx = leetcode162.solution2(nums);
        System.out.println(idx);
    }

    public int solution2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > nums[mid + 1]) {
                // 收缩右边界
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 二分
     */
    public int solution1(int[] nums) {
        // right取n-1可以防止mid+1越界
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 暴力解法
     */
    public int solution0(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length > 0 ? nums.length - 1 : -1;
    }
}
