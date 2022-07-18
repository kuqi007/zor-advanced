package com.zor.algorithm.interview.online;

import com.zor.algorithm.leetcode.tree.TreeNode;
import com.zor.algorithm.leetcode.tree.TreeNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * autodesk面试2022/07/13
 * 很深的树，如何判断两棵树是否相等
 */
public class SameTree {
    public static void main(String[] args) {
        SameTree test = new SameTree();
        TreeNode p1 = TreeNodeUtil.constructBinaryTree(1, 2, 5, 1, 6);
        TreeNode p2 = TreeNodeUtil.constructBinaryTree(1, 2, 5, 1, 6);
        boolean ans = test.test(p1, p2);
        System.out.println(ans);

    }

    public boolean test(TreeNode p1, TreeNode p2) {
        return dfs(p1, p2);
    }

    private boolean dfs(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) return true;

        if (p1 == null || p2 == null) {
            return false;
        }

        boolean mid = p1.val == p2.val;

        boolean left = dfs(p1.left, p2.left);

        boolean right = dfs(p1.right, p2.right);

        return mid && left && right;

    }

    private boolean bfs(TreeNode p1, TreeNode p2) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(p1);
        stack.push(p2);
        while (!stack.isEmpty()) {
            TreeNode n1 = stack.pop();
            TreeNode n2 = stack.pop();

            if (n1 == null && n2 == null) continue;

            else if (n1 == null || n2 == null) return false;

            else if (n1.val != n2.val) return false;

            stack.push(n1.left);
            stack.push(n2.left);
            stack.push(n1.right);
            stack.push(n2.right);
        }

        return true;

    }
}
