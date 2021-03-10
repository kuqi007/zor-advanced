package com.zor.algorithm.leetcode;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 *
 * @author zqq
 * @date 2021/3/10
 */
public class Leetcode125 {

    public static void main(String[] args) {

        String a = "0P";
        boolean palindrome = isPalindrome(a);
        System.out.println(palindrome);

    }

    public static boolean isPalindrome(String s) {

        // 数字ascii码是48-57
        // 大写字母是 65-90
        // 小写字母是97-122
        char[] chars = s.toCharArray();

        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (!Character.isLetterOrDigit(chars[l]) && l < r) {
                l++;
            }
            while (!Character.isLetterOrDigit(chars[r]) && l < r) {
                r--;
            }
            char lc = chars[l];
            char rc = chars[r];
            if (Character.toLowerCase(lc) != Character.toLowerCase(rc)) {
                return false;
            }
            l++;
            r--;
        }

        return true;

    }

    private static boolean isNotAlphaOrNum(char c) {
        return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9');
    }


}
