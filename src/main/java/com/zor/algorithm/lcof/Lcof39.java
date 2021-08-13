package com.zor.algorithm.lcof;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 * <p>
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 50000
 * Created by kuqi0 on 2021/8/12
 */
public class Lcof39 {

    /**
     * https://leetcode-cn.com/problems/majority-element/solution/3chong-fang-fa-by-gfu-2/
     * 摩尔投票法
     */
    public int solution4(int[] nums) {
        int cand_num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cand_num == nums[i]) {
                count++;
            } else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }


    /**
     * 随机化
     */
    public int solution3(int[] nums) {
        Random random = new Random();
        int majorCount = nums.length / 2;
        while (true) {
            int candidate = nums[randomRange(random, 0, nums.length)];
            int count = countOccurrence(nums, candidate);
            if (count > majorCount) {
                return candidate;
            }
        }
    }

    private int countOccurrence(int[] nums, int num) {
        int count = 0;
        for (int i : nums) {
            if (i == num) {
                count++;
            }
        }
        return count;
    }

    private int randomRange(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
    }


    public int solution2(int[] nums) {
        Map<Integer, Integer> counters = countNums(nums);
        Map.Entry<Integer, Integer> majorEntry = null;
        for (Map.Entry<Integer, Integer> entrySet : counters.entrySet()) {
            if (majorEntry == null || entrySet.getValue() > majorEntry.getValue()) {
                majorEntry = entrySet;
            }
        }
        return majorEntry.getKey();
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        return counter;
    }


    public int solution1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
