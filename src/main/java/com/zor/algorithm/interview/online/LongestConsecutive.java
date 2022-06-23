package com.zor.algorithm.interview.online;

import java.util.Arrays;

/**
 * leetcode128
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * Created by kuqi0 on 2022/6/23
 */
public class LongestConsecutive {

    public static void main(String[] args) {

//        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] nums = {1,2,0,1};
        int max = solution1(nums);
        System.out.println(max);


    }


    public static int getMax(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0, r = 0;
        int max = 0;
        while (r < n) {
            // 不连续
            while (l <= r && nums[r] - nums[l] != r - l) {
                l++;
            }
            max = Math.max(r - l + 1, max);
            r++;
        }
        return max;
    }

    public static int solution1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int cur = 1, max = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                cur++;
            } else {
                cur = 1;
            }

            max = Math.max(cur, max);

        }

        return max;

    }

}
