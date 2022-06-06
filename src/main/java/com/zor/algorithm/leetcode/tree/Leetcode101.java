package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 * create by tm on 2022/6/6
 */
public class Leetcode101 {
    public static void main(String[] args) {
        Leetcode101 leetcode101 = new Leetcode101();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 2, 2, null, 3, null, 3);
        boolean ans = leetcode101.bfs(root);
        System.out.println(ans);
    }

    public boolean bfs(TreeNode root) {
        if (root == null) return true;
        // 用队列或者栈都可以
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 都为空，说明是对称的
            if (node1 == null && node2 == null) continue;
            // 左右一个节点不为空，或者都不为空但数值不相同，返回false
            if (node1 == null || node2 == null || node1.val != node2.val) return false;

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compare(root.left, root.right);
    }

    /**
     * 比较这两棵树是否对称
     */
    private boolean compare(TreeNode left, TreeNode right) {
        // 首选排除节点为空的情况
        if (left == null && right == null) return true;
        else if (left == null) return false;
        else if (right == null) return false;
            // 排除了空节点，剩下就是值不相等的情况
        else if (left.val != right.val) return false;

        // 此时就剩：左右节点不为空，且数字相等的情况
        // 再做递归，做下一层的判断
        boolean outside = compare(left.left, right.right);  // 左子树：左、 右子树：右
        boolean inside = compare(left.right, right.left);   // 左子树：右、 右子树：左
        return outside && inside;                           // 左子树：中、 右子树：中 （逻辑处理）
    }
}
