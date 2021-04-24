package com.zor.algorithm.coding;

import java.util.Deque;
import java.util.LinkedList;

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

        String s = "3*2+2";
        System.out.println("calculate(s) = " + calculate(s));

    }

    /**
     * 主要思想如下：
     * <p>
     * 1. 将减法转化为加法（取相反数）
     * <p>
     * 2. 由于乘除法优先级高，直接计算
     * <p>
     * 3. 整数不仅一位，会>10
     * <p>
     * 4. 表达式中没有括号
     */
    public static int calculate(String s) {
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        char preSign = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                // 连续数字字符串转为数字，比如字符串 “23”，num 第一次保存为2，num第二次保存 2*10 + 3 = 23。
                num = num * 10 + c - '0';
            }
            // 如果是运算符或者是最后一个字符
            if ((!Character.isDigit(c) && c != ' ') || i == n - 1) {
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
                // num用完归零
                num = 0;
            }
        }
        // 将栈中元素求和
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
