package com.zor.algorithm.leetcode.tree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * <p>
 * <p>
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * create by tm on 2022/6/14
 */
public class Leetcode230 {

    /**
     * 迭代写法
     */
    public int solution1(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                k--;
                cur = stack.pop();
                if (k == 0) {
                    return cur.val;
                }
                cur = cur.right;
            }
        }
        return -1;
    }

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    int k;
    int res;

    /**
     * 递归
     */
    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        k--;
        if (k == 0) {
            res = root.val;
            return;
        }
        dfs(root.right);
    }
}
