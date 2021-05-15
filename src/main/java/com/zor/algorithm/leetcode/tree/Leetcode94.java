package com.zor.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuqi0 on 2021/5/15
 */
public class Leetcode94 {


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
