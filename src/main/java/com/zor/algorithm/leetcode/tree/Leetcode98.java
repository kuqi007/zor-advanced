package com.zor.algorithm.leetcode.tree;


import com.zor.algorithm.leetcode.tree.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 *
 * @author zqq
 * @date 2021/1/8
 */
public class Leetcode98 {

    private long pre = Long.MIN_VALUE;

    /**
     * TODO 树相关不够熟悉
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 中序遍历
        // 先访问左子树，判断左子树是否符合条件
        if (!isValidBST(root.left)) {
            return false;
        }

        // 判断当前节点是否满足条件
        if (root.val <= pre) {
            return false;
        }

        pre = root.val;

        // 再访问右子树，判断右子树是否符合条件
        return isValidBST(root.right);
    }

}
