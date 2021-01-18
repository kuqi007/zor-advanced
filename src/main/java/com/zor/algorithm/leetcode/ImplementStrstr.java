package com.zor.algorithm.leetcode;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 *
 * @author zqq
 * @date 2021/1/18
 */
public class ImplementStrstr {

    public static void main(String[] args) {
        System.out.println(strStr("smiles", "es"));

    }

    /**
     * 子串逐一比较 - 线性时间复杂度
     */
    public static int strStr(String haystack, String needle) {
        if (haystack == null) return -1;
        if (needle == null) return 0;

        int L = needle.length(), n = haystack.length();
        for (int start = 0; start < n - L + 1; start++) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;
    }


}
