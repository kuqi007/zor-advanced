package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 * Created by kuqi0 on 2021/5/7
 */
public class Leetcode104 {
    public static void main(String[] args) {
        Leetcode104 leetcode104 = new Leetcode104();
        TreeNode root = TreeNodeUtil.constructBinaryTree(3, 9, 20, null, null, 15, 7);
        System.out.println(leetcode104.solution1(root));
    }


    public int solution1(TreeNode root) {
        if (root == null) return 0;
        dfs2(root, 1);
        return result;
    }


    public void dfs2(TreeNode root, Integer depth) {
        result = Math.max(depth, result);
        if (root.left == null && root.right == null) {
            return;
        }
        // 左
        if (root.left != null) {
            dfs2(root.left, depth+1);
        }
        // 右
        if (root.right != null) {
            dfs2(root.right, depth+1);
        }

    }

    private int result = 0;

    /**
     * 最容易理解的递归和回溯
     */
    public void dfs1(TreeNode root, Integer depth) {
        result = Math.max(depth, result);
        if (root.left == null && root.right == null) {
            return;
        }
        // 左
        if (root.left != null) {
            // 深度+1
            depth++;
            dfs1(root.left, depth);
            // 回溯，深度-1
            depth--;
        }
        // 右
        if (root.right != null) {
            // 深度+1
            depth++;
            dfs1(root.right, depth);
            // 回溯，深度-1
            depth--;
        }

    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    public int bfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

    /**
     * bfs 层序遍历
     */
    public int levelOrder(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            ans++;
        }
        return ans;
    }
}
