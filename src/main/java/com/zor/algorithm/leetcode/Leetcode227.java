package com.zor.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 整数除法仅保留整数部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3+2*2"
 * 输出：7
 * 示例 2：
 * <p>
 * 输入：s = " 3/2 "
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 * @author zhuqiqi03
 * @date 2021/4/29
 */
public class Leetcode227 {

    public static void main(String[] args) {
        Leetcode227 leetcode227 = new Leetcode227();
        int calculate = leetcode227.calculate("3+2*2");
        System.out.println("calculate = " + calculate);

    }

    public int calculate(String s) {
        int num = 0;
        char preSign = '+';
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                preSign = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
