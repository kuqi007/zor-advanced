package com.zor.algorithm.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 * Created by kuqi0 on 2022/5/14
 */
public class Leetcode219 {


    /**
     * 跟用map直接遍历的区别就是：这里先剔除不符合条件的元素，那么后面找到相同元素，必然是满足条件的。。。
     */
    public boolean test1(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;
        Set<Integer> set = new HashSet<>();
        while (r < n) {
            // 去掉不满足的元素，r-l大于k
            if (r - l > k) {
                set.remove(nums[l]);
                l++;
            }
            // 此时set里都是满足条件的，判断有没有重复的元素
            if (!set.add(nums[r])) {
                return true;
            }
            r++;
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // map存储已经遍历过的下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                if (i - map.get(num) <= k) {
                    return true;
                }
            }
            map.put(num, i);
        }

        return false;
    }


    /**
     * 滑动窗口解法
     */
    public boolean solution1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            // 窗口中nums[i]已经存在，则返回true
            if (!set.add(nums[i])) {
                return true;
            }
        }

        return false;
    }
}
