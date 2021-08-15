package com.zor.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 * <p>
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * Created by kuqi0 on 2021/8/13
 */
public class Leetcode229 {

    public static void main(String[] args) {

    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int cand1 = nums[0], cand2 = nums[0];
        int count1 = 0, count2 = 0;
        for (int num : nums) {
            if (count1 > 0 && num == cand1) {
                count1++;
            } else if (count2 > 0 && num == cand2) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1++;
            } else if (count2 == 0) {
                cand2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        if (count1 > n / 3) res.add(cand1);
        if (count2 > n / 3) res.add(cand2);

        return res;
    }


    /**
     * 摩尔投票法
     */
    public List<Integer> solution2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // 初始化两个候选人candidate，和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;

        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第一个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第二个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }
            // 如果不符合以上任意一种情况，则直接减一
            count1--;
            count2--;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) count1++;
            else if (cand2 == num) count2++;
        }

        if (count1 > nums.length / 3) result.add(cand1);
        if (count2 > nums.length / 3) result.add(cand2);

        return result;
    }

    /**
     * 暴力解法
     */
    public List<Integer> solution1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int majorCnt = nums.length / 3;
        Map<Integer, Integer> counters = countNums(nums);
        for (Map.Entry<Integer, Integer> entry : counters.entrySet()) {
            if (res.size() > 2) {
                return res;
            }
            if (entry.getValue() > majorCnt) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        return counter;
    }


}
