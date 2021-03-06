package com.zor.algorithm.geekbang.stack.calculator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基本计算器通关
 * Created by kuqi0 on 2021/5/30
 */
public class BasicCalculator {

    public static void main(String[] args) {
        BasicCalculator basicCalculator = new BasicCalculator();
        String s = " 2-1 + 2 ";
        int res = basicCalculator.calc_03(s);
        System.out.println(res);

    }

    /**
     * 仅包含 + - 的计算器
     * 1+2-3+4
     *
     * @param s 输入
     * @return 结果
     */
    public int calc_01(String s) {
        int n = s.length();
        // 使用栈保存每一个数字
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        char preSign = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((c != ' ' && !Character.isDigit(c)) || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
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

    /**
     * 包含 + - * / 的计算器
     * 2-3*4+5
     *
     * @param s 输入
     * @return 结果
     */
    public int calc_02(String s) {
        int n = s.length();
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        int preSign = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((c != ' ' && !Character.isDigit(c)) || i == n - 1) {
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

    /** TODO: 2021/5/30 https://leetcode-cn.com/problems/basic-calculator-ii/solution/shi-yong-shuang-zhan-jie-jue-jiu-ji-biao-c65k/
     * 双栈做法
     * 包含 + - * / ( ) 的计算器
     *
     * @param s 输入
     * @return 结果
     */
    public int calc_03(String s) {

        return 0;
    }


    /**
     * 递归做法
     * 包含 + - * / ( ) 的计算器
     *
     * @param s 输入
     * @return 结果
     */
    public int calc_04(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i));
        }
        return dfs(queue);
    }

    /**
     * 递归写法，性能较差
     * 参考https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484903&idx=1&sn=184beaad36a71c9a8dd93c41a8ba74ac&chksm=9bd7fbefaca072f9beccff92a715d92ee90f46c297277eec10c322bc5ccd053460da6afb76c2&scene=21#wechat_redirect
     */
    private int dfs(Queue<Character> queue) {
        Deque<Integer> stack = new LinkedList<>();
        int preSign = '+';
        int num = 0;
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = dfs(queue);
            }
            // 当c遇到运算符或者遇到最后一个字符时
            if ((c != ' ' && !Character.isDigit(c)) || queue.isEmpty()) {
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
            if (c == ')') {
                break;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }


}
