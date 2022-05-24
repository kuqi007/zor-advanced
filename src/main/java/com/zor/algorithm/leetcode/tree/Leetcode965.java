package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 965. 单值二叉树
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：[2,2,2,5,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定树的节点数范围是 [1, 100]。
 * 每个节点的值都是整数，范围为 [0, 99] 。
 * Created by kuqi0 on 2022/5/24
 */
public class Leetcode965 {

    /**
     * 递归
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;

        boolean left = isUnivalTree(root.left);

        boolean right = isUnivalTree(root.right);


        boolean leftEq = true, rightEq = true;
        if (root.left != null) {
            leftEq = (root.val == root.left.val);
        }
        if (root.right != null) {
            rightEq = (root.val == root.right.val);
        }
        return leftEq && rightEq && left && right;
    }

    /**
     * 递归
     */
    public boolean isUnivalTree1(TreeNode root) {
        return dfs(root, root.val);
    }

    public boolean dfs(TreeNode root, int target) {
        if (root == null) return true;
        if (root.val != target) {
            return false;
        }
        return dfs(root.left, target) && dfs(root.right, target);
    }


    /**
     * bfs
     */
    public boolean bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        TreeNode pre = root;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != pre.val) {
                return false;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            pre = node;
        }

        return true;
    }


}
