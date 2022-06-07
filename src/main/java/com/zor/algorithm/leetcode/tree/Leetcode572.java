package com.zor.algorithm.leetcode.tree;

/**
 * 572. 另一棵树的子树
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 * create by tm on 2022/6/6
 */
public class Leetcode572 {
    public static void main(String[] args) {
        Leetcode572 leetcode572 = new Leetcode572();
        TreeNode root = TreeNodeUtil.constructBinaryTree(3, 4, 5, 1, 2);
        TreeNode sub = TreeNodeUtil.constructBinaryTree(4, 1, 2);
        boolean isSub = leetcode572.isSubtree(root, sub);
        System.out.println(isSub);

    }

    /**
     * todo 递归性能较差，寻找性能更好的做法
     */
    public boolean solution1(TreeNode root, TreeNode subRoot){

        return false;
    }

    /**
     * dfs暴力匹配
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null) return false;
        // 当前节点是否和subRoot相等
        boolean isSame = compare(root, subRoot);
        // 左子树是否跟subRoot相等
        boolean left = isSubtree(root.left, subRoot);
        // 右子树是否跟subRoot相等
        boolean right = isSubtree(root.right, subRoot);
        return isSame || left || right;
    }

    private boolean compare(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;

        boolean left = compare(p.left, q.left);
        boolean right = compare(p.right, q.right);
        return left && right;
    }
}
