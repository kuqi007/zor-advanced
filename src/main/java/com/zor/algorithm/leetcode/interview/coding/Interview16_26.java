package com.zor.algorithm.leetcode.interview.coding;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * <p>
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * <p>
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 * <p>
 * 链接：https://leetcode-cn.com/problems/calculator-lcci
 *
 * @author zqq
 * @date 2021/3/26
 */
public class Interview16_26 {
    public static void main(String[] args) {

    }

    public int calculate(String s) {
        Deque<Character> operator = new LinkedList<>();
        Deque<Character> nums = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // 纯数字
                nums.push(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // 运算符


            }
        }

        return 0;
    }
}
