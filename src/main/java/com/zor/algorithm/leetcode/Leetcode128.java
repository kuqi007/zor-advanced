package com.zor.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * Created by kuqi0 on 2022/6/28
 */
public class Leetcode128 {
    public static void main(String[] args) {
        Leetcode128 leetcode128 = new Leetcode128();
        int nums[] = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int i = leetcode128.longestConsecutive(nums);
        System.out.println(i);
    }

    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            // 如果发现比num小的直接跳过，因为从更小的遍历肯定比从num遍历要长
            if (set.contains(num - 1)) continue;
            int cur = num;
            while (set.contains(cur + 1)) {
                cur++;
            }
            // 因为是连续序列，直接计算长度即可
            ans = Math.max(ans, cur - num + 1);
        }
        return ans;
    }
}
