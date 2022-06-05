package com.zor.algorithm.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 * <p>
 * Created by kuqi0 on 2021/5/12
 */
public class Leetcode110 {

    public static void main(String[] args) {
        Leetcode110 leetcode111 = new Leetcode110();
        TreeNode root = TreeNodeUtil.constructBinaryTree(3, 9, 20, null, null, 15, 7);
        TreeNodeUtil.show(root);
        System.out.println(leetcode111.solution3(root));

    }


    public boolean solution3(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // middle
            if (Math.abs(getDepth(node.left) - getDepth(node.right)) > 1) {
                return false;
            }
            // right
            if (node.right != null) stack.push(node.right);
            // left
            if (node.left != null) stack.push(node.left);
        }
        return true;
    }


    private int getDepth(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        int result = 0;
        int depth = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur != null) {
                stack.push(cur);
                stack.push(null);
                depth++;
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            } else {
                stack.pop();
                depth--;
            }
            result = Math.max(result, depth);
        }
        return result;
    }

    public boolean solution2(TreeNode root) {
        return getHeight(root) != -1;
    }

    /**
     * 简化之后的递归
     */
    private int getHeight1(TreeNode root) {
        if (root == null) return 0;
        int l = getHeight1(root.left);
        if (l == -1) return -1;
        int r = getHeight1(root.right);
        if (r == -1) return -1;
        return Math.abs(l - r) > 1 ? -1 : Math.max(l, r) + 1;

    }

    /**
     * 好理解的递归，-1代表为非平衡树
     */
    private int getHeight(TreeNode root) {
        // 明确终止条件
        if (root == null) {
            return 0;
        }
        // 左子树高度
        int l = getHeight(root.left);
        // 如果左子树不是平衡树，直接返回-1
        if (l == -1) return -1;
        int r = getHeight(root.right);
        // 如果左子树不是平衡树，直接返回-1
        if (r == -1) return -1;
        // 左右子树高度差大于1，返回-1
        if (Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
    }


    /**
     * 自底向上
     * <a href="https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/">https://leetcode-cn.com/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/</a>
     */
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }


    /**
     * 从顶至底 | 自顶向下（暴力法）
     */
    public boolean solution1(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && solution1(root.left) && solution1(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    private static class ReturnNode {
        boolean isB;
        int depth;

        public ReturnNode(int depth, boolean isB) {
            this.depth = depth;
            this.isB = isB;
        }
    }

    /**
     * 这个递归太令人疑惑了
     * <a href="https://lyl0724.github.io/2020/01/25/1/">三道题解决递归问题</a>
     */
    public ReturnNode isBST(TreeNode root) {
        if (root == null) {
            return new ReturnNode(0, true);
        }
        ReturnNode left = isBST(root.left);
        ReturnNode right = isBST(root.right);
        if (!left.isB || !right.isB) {
            return new ReturnNode(0, false);
        }
        if (Math.abs(left.depth - right.depth) > 1) {
            return new ReturnNode(0, false);
        }
        return new ReturnNode(Math.max(left.depth, right.depth) + 1, true);
    }


}
