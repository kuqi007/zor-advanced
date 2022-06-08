package com.zor.algorithm.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 * <p>
 * 输入: root = [1]
 * 输出: 0
 * <p>
 * <p>
 * 提示:
 * <p>
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 * create by tm on 2022/6/8
 */
public class Leetcode404 {

    public static void main(String[] args) {
        Leetcode404 leetcode404 = new Leetcode404();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 2, 3, 4, 5);
        int i = leetcode404.solution2(root);
        System.out.println(i);
    }

    /**
     * 迭代法，中序遍历
     */
    public int solution2(TreeNode root) {
        int sum = 0;
        if (root == null) return 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                //System.out.println(cur.val);
                if (cur.left != null && cur.left.left == null && cur.left.right == null) {
                    sum += cur.left.val;
                }
                cur = cur.right;
            }
        }
        return sum;

    }

    public int sumOfLeftLeaves(TreeNode root) {
        // 空节点，返回0
        if (root == null) return 0;
        // 左
        int left = sumOfLeftLeaves(root.left);
        // 右
        int right = sumOfLeftLeaves(root.right);
        // 中
        int mid = 0;
        // 左节点
        if (root.left != null && root.left.left == null && root.left.right == null) {
            mid = root.left.val;
        }
        return left + right + mid;
    }

    private int sum;

    public int solution1(TreeNode root) {
        dfs(root);
        return sum;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        dfs(root.left);
        dfs(root.right);
    }

}
