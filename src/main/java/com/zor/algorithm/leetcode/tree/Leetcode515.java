package com.zor.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * <p>
 * <p>
 * 示例1：
 * <p>
 * <p>
 * <p>
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 * <p>
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 * <p>
 * Created by kuqi0 on 2022/6/5
 */
public class Leetcode515 {
    public static void main(String[] args) {
        Leetcode515 leetcode515 = new Leetcode515();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 3, 2, 5, 3, null, 9);
        System.out.println(leetcode515.largestValues(root));

    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int pre = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                pre = Math.max(pre, node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(pre);
        }

        return res;
    }
}
