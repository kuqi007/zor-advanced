package com.zor.algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
        String s = "(1+(4+5+2)-3)+(6+8)";
        int calculate = leetcode224.calculate1(s);
        System.out.println(calculate);
    }

    public int calculate1(String s) {
        char[] cs = s.toCharArray();
        Queue<Character> queue = new LinkedList<>();
        for (char c : cs) {
            queue.add(c);
        }
        return dfs(queue);
    }

    private int dfs(Queue<Character> queue) {
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        char prev = '+';
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c == '(') {
                num = dfs(queue);
            }
            // 如果是运算符或者是右括号或者是最后一个符号
            if ("+-*/".contains(c + "") || c == ')' || queue.isEmpty()) {
                switch (prev) {
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
                num = 0;
                prev = c;
            }
            if (c == ')') {
                break;
            }

        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 双栈做法
     */
    public int calculate0(String s) {
        // 存放数字
        Deque<Integer> nums = new LinkedList<>();
        // 由于第一个数可能是负数，为了减少边界判断。一个小技巧是先往 nums 添加一个 0
        nums.push(0);
        // 存放符号
        Deque<Character> ops = new LinkedList<>();
        // 替换掉所有空格
        s = s.replaceAll(" ", "");

        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peek();
                    if (op != '(') {
                        // 如果不是左括号，一直计算下去
                        calc(nums, ops);
                    } else {
                        // 如果是左括号，poll完退出循环
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (isDigit(c)) {
                    // 多位数字
                    int num = 0;
                    int j = i;
                    while (j < n && isDigit(cs[j])) {
                        num = num * 10 + cs[j++] - '0';
                    }
                    i = j - 1;
                    nums.push(num);
                } else {
                    // 这个分支是正常的运算符号
                    // 为防止 () 内出现的首个字符为运算符，将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
                    // 比如：1-(-2)
                    if (i > 0 && cs[i - 1] == '(') {
                        nums.push(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        calc(nums, ops);
                    }
                    ops.push(c);
                }
            }
        }

        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peek();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        // 如果数字栈为空，或者少于两个数字
        if (nums.isEmpty() || nums.size() < 2) return;
        // 没有符号
        if (ops.isEmpty()) return;
        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        // 计算完放到nums
        nums.push(op == '+' ? a + b : a - b);
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c);
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
