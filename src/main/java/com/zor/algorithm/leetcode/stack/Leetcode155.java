package com.zor.algorithm.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by kuqi0 on 2021/5/9
 */
public class Leetcode155 {

    //static class MinStack {
    //
    //    private Deque<Integer> xstack;
    //
    //    /**
    //     * 辅助栈做法
    //     */
    //    private Deque<Integer> minStack;
    //
    //    /**
    //     * initialize your data structure here.
    //     */
    //    public MinStack() {
    //        xstack = new LinkedList<>();
    //        minStack = new LinkedList<>();
    //        minStack.push(Integer.MAX_VALUE);
    //    }
    //
    //    public void push(int val) {
    //        xstack.push(val);
    //        minStack.push(Math.min(minStack.peek(), val));
    //    }
    //
    //    public void pop() {
    //        xstack.pop();
    //        minStack.pop();
    //    }
    //
    //    public int top() {
    //        return xstack.peek();
    //    }
    //
    //    public int getMin() {
    //        return minStack.peek();
    //    }
    //}

    static class MinStack {
        Deque<int[]> stack;

        public MinStack() {
            stack = new LinkedList<>();
        }

        public void push(int val) {
            if (stack.size() == 0) {
                stack.push(new int[]{val, val});
            } else {
                stack.push(new int[]{val, Math.min(val, stack.peek()[1])});
            }

        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }


    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(1);
        obj.pop();
        int param_3 = obj.top();
        int param_4 = obj.getMin();
    }

}
