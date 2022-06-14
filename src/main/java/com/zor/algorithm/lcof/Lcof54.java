package com.zor.algorithm.lcof;

import com.zor.algorithm.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * create by tm on 2022/6/14
 */
public class Lcof54 {

    public int solution2(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    int res;
    int k;

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);

        k--;
        if (k == 0) {
            res = root.val;
            return;
        }
        dfs(root.left);
    }

    public int solution1(TreeNode root, int k) {

        ArrayList<TreeNode> list = new ArrayList<>();
        dfs(root, list, k);
        return list.get(list.size() - 1).val;
    }

    private void dfs(TreeNode root, List<TreeNode> list, int k) {
        if (root == null) return;
        dfs(root.right, list, k);

        if (list.size() >= k) {
            return;
        }
        list.add(root);

        dfs(root.left, list, k);


    }

}
