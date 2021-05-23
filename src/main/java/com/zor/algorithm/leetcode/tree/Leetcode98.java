package com.zor.algorithm.leetcode.tree;


import java.util.Deque;
import java.util.LinkedList;

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

    public static void main(String[] args) {
        Leetcode98 leetcode98 = new Leetcode98();
        TreeNode node = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        boolean validBST = leetcode98.inorder(node);
        System.out.println(validBST);

    }

    private long pre = Long.MIN_VALUE;

    /**
     * 中序遍历
     */
    public boolean solution0(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= pre) {
            return false;
        }

        pre = root.val;

        return isValidBST(root.right);

    }

    /**
     * 中序遍历迭代写法
     */
    public boolean inorder(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        // 保存上一个节点的值
        long preNumber = Long.MIN_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.val <= preNumber) {
                    return false;
                }
                preNumber = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

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
