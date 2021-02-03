package com.zor.algorithm.leetcode.tree;

/**
 * @author zqq
 * @date 2021/2/3
 */
public class DFS {
    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
    }
}
