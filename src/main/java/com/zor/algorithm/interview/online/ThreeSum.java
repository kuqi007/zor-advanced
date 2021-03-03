package com.zor.algorithm.interview.online;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 百度面试，三数之和
 *
 * @author zqq
 * @date 2021/3/3
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9, 0};
        List<List<Integer>> sum = getSum(arr, 10);
        System.out.println(sum);
    }

    public static List<List<Integer>> getSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int num1 = nums[i];
            int left = i + 1, right = i + 2;
            while (left < right) {
                if (nums[left] + nums[right] + num1 == target) {
                    List<Integer> temp = Lists.newArrayList(num1, nums[left], nums[right]);
                    ans.add(temp);
                }
                left++;
                right++;
            }
        }
        return ans;
    }


}
