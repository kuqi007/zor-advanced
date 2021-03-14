package com.zor.algorithm.leetcode;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * Created by kuqi0 on 2021/3/14
 */
public class Leetcode43 {
    public static void main(String[] args) {
        String multiply = multiply("999", "0");
        System.out.println(multiply);
    }

    private static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int m = num1.length();
        int n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }

        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    /**
     * 做加法
     */
    public static String solution1(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        int n1 = num1.length();
        int n2 = num2.length();

        String res = "";
        for (int i = n2 - 1; i >= 0; i--) {
            StringBuilder tmp = new StringBuilder();
            int carry = 0;
            for (int j = n2 - 1; j > i; j--) {
                tmp.append(0);
            }
            int a = num2.charAt(i) - '0';
            for (int j = n1 - 1; j >= 0; j--) {
                int b = num1.charAt(j) - '0';
                int sum = carry + a * b;
                tmp.append(sum % 10);
                carry = sum / 10;
            }
            if (carry > 0) {
                tmp.append(carry);
            }
            res = addTwoString(res, tmp.reverse().toString());
        }
        return res;
    }

    private static String addTwoString(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }


}
