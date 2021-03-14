package com.zor.algorithm.lintcode;

/**
 * https://www.lintcode.com/problem/655
 * 描述
 * 以字符串的形式给出两个非负整数 num1 和 num2，返回 num1 和 num2 的和。
 * <p>
 * num1 和 num2 的长度都小于5100。
 * num1 和 num2 都只包含数字 0-9。
 * num1 和 num2 都不包含任何前导零。
 * 您不能使用任何内置的BigInteger库内的方法或直接将输入转换为整数。
 * 样例 1:
 * <p>
 * 输入 : num1 = "123", num2 = "45"
 * 输出 : "168"
 * <p>
 * Created by kuqi0 on 2021/3/6
 */
public class LintCode655 {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "45";
        String s = solution2(num1, num2);
        System.out.println(s);
    }

    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return sum of num1 and num2
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder res = new StringBuilder();
        // 进位
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
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
            // sum插入到res的前面
            res.insert(0, sum % 10);
            // sum%10+'0' 是把数字转换为字符串
            //res = (char) (sum % 10 + '0') + res;
            carry = sum / 10;
        }

        return res.toString();
    }

    public static String solution2(String num1, String num2) {
        StringBuilder sb = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) sum += num1.charAt(i--) - '0';
            if (j >= 0) sum += num2.charAt(j--) - '0';

            carry = sum / 10;
            sb.append(sum % 10);
        }

        if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
