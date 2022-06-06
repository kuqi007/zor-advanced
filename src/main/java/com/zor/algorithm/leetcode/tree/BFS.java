package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zqq
 * @date 2021/2/3
 */
public class BFS {

    void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            visit(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

    }

    private void visit(TreeNode root) {
        System.out.println(root.val);
    }
}
