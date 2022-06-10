package com.zor.algorithm.leetcode.tree;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * <p>
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * <p>
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * create by tm on 2022/6/10
 */
public class Leetcode543 {

    public static void main(String[] args) {
        Leetcode543 leetcode543 = new Leetcode543();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 2);
        System.out.println(leetcode543.diameterOfBinaryTree(root));
    }


    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxLen;
    }

    private int maxLen = 0;

    private int depth(TreeNode root) {
        if (root == null) return 0;

        int l = depth(root.left);
        int r = depth(root.right);
        // 最大直径 = 左子树深度+右子树深度
        maxLen = Math.max(maxLen, l + r);
        return Math.max(l, r) + 1;
    }
}
