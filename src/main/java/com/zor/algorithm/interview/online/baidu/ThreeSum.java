package com.zor.algorithm.interview.online.baidu;

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
        int[] arr = new int[]{0, 1, 1, 3, 3, 5, 7, 7, 8, 9};
        //int[] arr = new int[]{0, 0, 0};
        List<List<Integer>> sum = getSum(arr, 10);
        System.out.println(sum);
    }

    public static List<List<Integer>> getSum(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int num1 = nums[i];
            int left = i + 1, right = n - 1;
            while (left < right) {
                int temp = num1 + nums[left] + nums[right];
                if (temp == target) {
                    ans.add(Arrays.asList(num1, nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (temp < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }


}
