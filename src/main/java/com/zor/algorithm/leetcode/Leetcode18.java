package com.zor.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：答案中不可以包含重复的四元组。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Created by kuqi0 on 2021/3/14
 */
public class Leetcode18 {
    public static void main(String[] args) {
        int[] nums = {-2, -1, -1, 1, 1, 2, 2};
        List<List<Integer>> lists = fourSum(nums, 0);
        System.out.println(lists);

    }

    private static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 如果这个数加上最大的三个数小于target，那么直接进入下次循环
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 如果这个数加上最大的三个数小于target，那么直接进入下次循环
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }

                int l = j + 1, r = length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        l++;
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return ans;

    }

    /**
     * 四数之和转换为三数之和
     */
    public static List<List<Integer>> solution1(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
            int cur = nums[i];
            if (i > 0 && cur == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> threeSum = threeSum(nums, i + 1, target - cur);
            for (List<Integer> sum : threeSum) {
                sum.add(0, cur);
                ans.add(sum);
            }
        }
        return ans;
    }

    /**
     * 通用三数之和解法
     */
    private static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = start; i < n - 2; i++) {
            int cur = nums[i];
            // 这里i需要大于start，不要和start之前元素比较
            if (i > start && cur == nums[i - 1]) {
                continue;
            }
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = cur + nums[l] + nums[r];
                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    ans.add(new ArrayList<>(Arrays.asList(cur, nums[l], nums[r])));
                    while (l < r && nums[l + 1] == nums[l]) l++;
                    while (l < r && nums[r - 1] == nums[r]) r--;
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }
}
