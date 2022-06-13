package com.zor.algorithm.leetcode.tree;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * create by tm on 2022/6/10
 */
public class Leetcode124 {
    public static void main(String[] args) {
        Leetcode124 leetcode124 = new Leetcode124();
        TreeNode root = TreeNodeUtil.constructBinaryTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        System.out.println(leetcode124.solution1(root));
    }

    private int res = Integer.MIN_VALUE;

    public int solution1(TreeNode root) {
        dfs(root);
        return res;
    }

    /**
     * 计算每个节点的最大贡献值，叶子节点最大贡献值为自身的值，非叶子节点的贡献值等于max(左子树贡献值，右子树贡献值)+cur.val
     */
    public int dfs(TreeNode root) {
        // 节点为空，返回0
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        res = Math.max(res, left + right + root.val);
        // 计算单边贡献值
        return Math.max(left, right) + root.val;
    }
}
