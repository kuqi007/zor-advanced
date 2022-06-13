package com.zor.algorithm.leetcode.tree;

/**
 * 236. 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * create by tm on 2022/6/13
 */
public class Leetcode236 {

    public static void main(String[] args) {
        Leetcode236 leetcode236 = new Leetcode236();
        TreeNode root = TreeNodeUtil.constructBinaryTree(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
    }

    /**
     * <a href="https://www.programmercarl.com/0236.%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88.html#javascript">...</a>
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q || root == null) return root;
        // 必须遍历整棵树，不能提前返回，因为后面要用left和right的结果做判断
        // 左
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 右
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 中
        // 如果两边都找到了，那么root就是结果
        if (left != null && right != null) {
            return root;
        }
        // 左边没找到，右边找到了，返回右边
        if (left == null && right != null) {
            return right;
        }
        // 其他情况，返回left；left和right都为null，返回null（此时left=null）
        return left;
    }

}
