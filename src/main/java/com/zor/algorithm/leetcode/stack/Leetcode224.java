package com.zor.algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * Created by kuqi0 on 2021/5/24
 */
public class Leetcode224 {

    public static void main(String[] args) {
        Leetcode224 leetcode224 = new Leetcode224();
        String s = "1-(2-3)";
        int calculate = leetcode224.calculate(s);
        System.out.println(calculate);
    }

    /**
     * todo 没太看懂
     */
    public int calculate(String s) {
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        int sign = 1, res = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int cur = c - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    cur = cur * 10 + s.charAt(i) - '0';
                    i++;
                }
                res = res + sign * cur;

            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (c == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }

}
