package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 * <p>
 * <p>
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 * create by tm on 2022/6/6
 */
public class Leetcode100 {

    public static void main(String[] args) {
        Leetcode100 leetcode100 = new Leetcode100();
        TreeNode p = TreeNodeUtil.constructBinaryTree(1, 2, 3);
        TreeNode q = TreeNodeUtil.constructBinaryTree(1, 2, 3);
        boolean ans = leetcode100.solution1(p, q);
        System.out.println(ans);
    }

    public boolean solution1(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            if (n1 == null && n2 == null) continue;

            if (n1 == null || n2 == null || n1.val != n2.val) return false;

            queue.add(n1.left);
            queue.add(n2.left);
            queue.add(n1.right);
            queue.add(n2.right);
        }
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;

        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}
