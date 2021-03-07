package com.zor.algorithm.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 *
 * @author zqq
 * @date 2021/1/25
 */
public class Leetcode35 {

    public static void main(String[] args) {

        System.out.println("result" + searchInsert(new int[]{1, 3, 5, 6}, 4));

    }

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static int solution1(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int solution2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;// target 在左区间，所以[left, middle - 1]
            } else if (nums[mid] < target) {
                left = mid + 1;// target 在右区间，所以[middle + 1, right]
            } else {
                return mid;
            }
        }

        return right + 1;
    }

    public static int solution3(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            // 分别处理如下三种情况
            // 目标值在数组所有元素之前
            // 目标值等于数组中某一个元素
            // 目标值插入数组中的位置
            if (nums[i] >= target) {
                return i;
            }
        }
        // 目标值在数组所有元素之后的情况
        return nums.length;// 如果target是最大的，或者 nums为空，则返回nums的长度
    }


}
