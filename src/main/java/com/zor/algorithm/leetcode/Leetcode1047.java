package com.zor.algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author zqq
 * @date 2021/3/9
 */
public class Leetcode1047 {

    public static void main(String[] args) {


    }

    public String removeDuplicates(String S) {
        StringBuilder stack = new StringBuilder();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == c) {
                stack.deleteCharAt(top);
                top--;
            } else {
                stack.append(c);
                top++;
            }
        }
        return stack.toString();
    }


    public String solution1(String S) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        for (Character character : stack) {
            res.append(character);
        }
        //while (!stack.isEmpty()) {
        //    res.append(stack.pop());
        //}
        return res.toString();
    }

}
