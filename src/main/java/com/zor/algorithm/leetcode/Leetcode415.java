package com.zor.algorithm.leetcode;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * Created by kuqi0 on 2021/3/14
 */
public class Leetcode415 {
    public static void main(String[] args) {
        String s = solution3("155", "945");
        System.out.println(s);

    }

    /**
     * 重拾算法
     *
     * @date 2021年10月28日08:28:05
     */
    public static String solution3(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int a = 0, b = 0;
            if (i >= 0) {
                a = num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                b = num2.charAt(j) - '0';
                j--;
            }
            int sum = a + b + carry;
            carry = sum / 10;
            res.append(sum % 10);
        }
        return res.reverse().toString();
    }


    /**
     * @date 2021/4/23
     */
    public static String solution1(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int a = 0, b = 0;
            if (i >= 0) {
                // 算出某一位的值
                a = num1.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                b = num2.charAt(j) - '0';
                j--;
            }

            int sum = a + b + carry;
            ans.append(sum % 10);
            carry = sum / 10;
        }
        return ans.reverse().toString();

    }

    public static String addStrings(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        StringBuilder ans = new StringBuilder();
        int i = m - 1, j = n - 1, add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int a = 0;
            if (i >= 0) {
                a = num1.charAt(i) - '0';
                i--;
            }
            int b = 0;
            if (j >= 0) {
                b = num2.charAt(j) - '0';
                j--;
            }
            int sum = a + b + add;
            ans.append(sum % 10);
            add = sum / 10;
        }

        return ans.reverse().toString();
    }
}
