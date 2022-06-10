package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * <p>
 * 假设二叉树中至少有一个节点。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 * create by tm on 2022/6/9
 */
public class Leetcode513 {

    public static void main(String[] args) {
        Leetcode513 leetcode513 = new Leetcode513();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 2, 3, 4, null, 5, 6, null, null, 7);
        System.out.println(leetcode513.levelOrder(root));
    }

    public int solution1(TreeNode root) {
        dfs(root, 0);

        return maxLeftVale;
    }

    private int maxDepth = Integer.MIN_VALUE;
    private int maxLeftVale = 0;

    /**
     * 求深度那么用 前序遍历
     */
    public void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                maxLeftVale = root.val;
            }
            return;
        }

        if (root.left != null) {
            dfs(root.left, depth + 1);
        }
        if (root.right != null) {
            dfs(root.right, depth + 1);
        }
    }


    /**
     * 层序遍历
     */
    public int levelOrder(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    res = node.val;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;

    }
}
