package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 395. 至少有K个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 * Created by kuqi0 on 2021/2/27
 */
public class Leetcode395 {
    public static void main(String[] args) {

    }

    /**
     * 递归写法
     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.merge(s.charAt(i), 1, Integer::sum);
        }

        for (char c : counter.keySet()) {
            if (counter.get(c) < k) {
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstring(t, k));
                }
                return res;
            }
        }
        return s.length();
    }

    /**
     * TODO
     * 滑动窗口
     */
    public int solution1(String s, int k) {
        int n = s.length();
        int left = 0, right = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < n) {

            char c = s.charAt(right);

            //while () {
            //    left++;
            //}

            map.merge(c, 1, Integer::sum);

            ans = Math.max(ans, right - left + 1);
            right++;
        }


        return ans;
    }


}
