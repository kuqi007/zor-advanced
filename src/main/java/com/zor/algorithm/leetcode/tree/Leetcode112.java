package com.zor.algorithm.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * \112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * Created by kuqi0 on 2021/5/23
 */
public class Leetcode112 {

    public static void main(String[] args) {
        Leetcode112 leetcode = new Leetcode112();
        TreeNode root = TreeNodeUtil.constructBinaryTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        boolean b = leetcode.solution2(root, 22);
        System.out.println(b);
    }

    public boolean solution2(TreeNode root, int targetSum) {
        return dfs(root, 0, targetSum);
    }

    public boolean dfs(TreeNode root, int cur, int targetSum) {
        if (root == null) return false;
        int ncur = root.val + cur;
        // 退出条件
        if (root.left == null && root.right == null) {
            return ncur == targetSum;
        }

        return dfs(root.left, ncur, targetSum) || dfs(root.right, ncur, targetSum);
    }

    /**
     * 前序遍历迭代做法
     */
    public boolean solution1(TreeNode root, int targetSum) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            int curVal = cur.val;
            if (cur.left != null) {
                cur.left.val = curVal + cur.left.val;
                stack.push(cur.left);
            }
            if (cur.right != null) {
                cur.right.val = curVal + cur.right.val;
                stack.push(cur.right);
            }

            if (cur.left == null && cur.right == null && curVal == targetSum) {
                return true;
            }
        }

        return false;
    }

    /**
     * 深度优先遍历
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 广度优先遍历
     */
    public boolean bfs(TreeNode root, int targetSum) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> sum = new LinkedList<>();
        queue.add(root);
        sum.add(root.val);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int preSum = sum.poll();
            if (cur.left == null && cur.right == null) {
                if (preSum == targetSum) {
                    return true;
                }
                continue;
            }
            if (cur.left != null) {
                queue.add(cur.left);
                sum.add(preSum + cur.left.val);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                sum.add(preSum + cur.right.val);
            }
        }
        return false;
    }


}
