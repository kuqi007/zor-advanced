package com.zor.algorithm.leetcode.tree;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 * create by tm on 2022/6/17
 */
public class Leetcode437 {
    public static void main(String[] args) {

    }


    /**
     * todo 前缀和
     */
    public int preSum(TreeNode root, int targetSum) {

        return targetSum;
    }

    /**
     * 由上到下使用前序遍历
     * 暴力-双重递归，时间复杂度n^2
     */
    public int dfs(TreeNode root, int targetSum) {
        if (root == null) return 0;
        // 以当前节点为起点，找符合条件的路劲
        sum(root, targetSum);

        // 以左、右孩子为起点，找符合条件路劲
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        return res;
    }

    private int res;

    // 遍历整棵树所以不需要返回值
    private void sum(TreeNode root, int sum) {
        if (root == null) return;
        sum -= root.val;
        if (sum == 0) {
            // 这里拿到符合条件的路劲不能return，因为往下走可能还有符合条件的，比如
            // [1,-2,-3,1,3,-2,null,-1] 寻找sum为-1
            res++;
        }
        sum(root.left, sum);
        sum(root.right, sum);
    }
}
