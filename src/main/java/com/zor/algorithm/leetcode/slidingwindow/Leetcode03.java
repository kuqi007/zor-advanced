package com.zor.algorithm.leetcode.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * No.3
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class Leetcode03 {

    public static void main(String[] args) {

        int count = solution3("pwwkew");
        System.out.println(count);

    }

    /**
     * 滑动窗口，使用大小128的数组比hashset性能高很多
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, right = 0;
        int max = 0;
        // 数组表示是否重复
        int[] arr = new int[128];
        while (right < n) {
            char c = s.charAt(right);
            while (arr[c] == 1) {
                arr[s.charAt(left)] = 0;
                left++;
            }

            arr[c] = 1;
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    /**
     * 用hashset性能有所下降
     */
    public static int solution2(String s) {
        int n = s.length();
        int left = 0, right = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (right < n) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    public static int solution3(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static int solution4(String s) {
        int n = s.length();

        int[] last = new int[128];

        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                last[s.charAt(i - 1)] = 0;
            }
            while (rk + 1 < n && last[s.charAt(rk + 1)] == 0) {
                last[s.charAt(rk + 1)] = 1;
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    /**
     * TODO
     * 暴力破解
     *
     * @param s
     * @return
     */
    private static int violentCrack(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            boolean isDuplicate = false;
            for (int j = i; j < s.length(); j++) {
                if (!set.add(s.charAt(j))) {
                    // 如果出现重复字符，应当把这个字符的长度去掉，本来为j-i+1，现在应为j-i
                    isDuplicate = true;
                    break;
                }
            }
            maxLength = isDuplicate ? set.size() - 1 : set.size();

        }
        return maxLength;
    }
}
