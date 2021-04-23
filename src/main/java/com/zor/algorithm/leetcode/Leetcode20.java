package com.zor.algorithm.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * No.20 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by kuqi0 on 2021/1/2
 */
public class Leetcode20 {
    public static void main(String[] args) {

        System.out.println(solution1("(("));

    }

    /**
     * 这个解法有点秀，性能最好
     */
    public static boolean bestSolution(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean solution4(String s) {
        if (s.equals("") || s.length() % 2 == 1) {
            return false;
        }
        //栈：先进后出
        Deque<Character> stk = new LinkedList<>();
        //循环遍历字符串中的每一个括号，栈空时入栈
        //栈不空时判断是否与栈顶元素构成有效的括号
        //是，则栈顶元素出栈
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!stk.isEmpty()) {
                char t = stk.peek();
                if (t == '(' && ch == ')' || t == '[' && ch == ']' || t == '{' && ch == '}') {
                    stk.pop();
                    continue;
                }
            }
            stk.push(ch);
        }
        //只能用栈空来判断括号最终是否有效
        return stk.isEmpty();

    }


    public static boolean solution1(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ("]})".contains(String.valueOf(c))) {
                Character peek = stack.peek();
                if (stack.isEmpty() || peek != getMatch(c)) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();

    }

    private static Character getMatch(Character c) {
        Character result = null;
        switch (c) {
            case ')':
                result = '(';
                break;
            case ']':
                result = '[';
                break;
            case '}':
                result = '{';
                break;
        }
        return result;
    }

    public static boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 存在表示是右括号
            if (pairs.containsKey(c)) {
                // 如果栈为空表示先出现了右括号，那么直接返回false，或者栈头不是匹配对应括号，也返回false
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static boolean solution2(String s) {

        if (s.length() % 2 == 1) return false;

        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(']', '[');
        pairs.put(')', '(');
        pairs.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 出现右括号
            if (pairs.containsKey(c)) {
                // 栈为空，未发现左括号，或者栈顶与该括号不匹配
                if (stack.isEmpty() || pairs.get(c) != stack.peek()) {
                    return false;
                }
                stack.pop();
            } else {
                // 非右括号
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }


}
