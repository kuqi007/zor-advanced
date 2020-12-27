package com.zor.algorithm.leetcode;

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
// TODO
public class LengthOfLongestSubstring {

    public static void main(String[] args) {

        int count = violentCrack("au");
        System.out.println(count);

    }

    public int lengthOfLongestSubstring(String s) {
        int count = 0;
        while (s.length() > 0) {


        }


        return count;
    }

    /**
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
