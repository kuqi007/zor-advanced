package com.zor.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：["1"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 */
public class Leetcode257 {
    public static void main(String[] args) {
        Leetcode257 leetcode257 = new Leetcode257();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 2, 3, null, 5);
        List<String> res = leetcode257.solution2(root);
        System.out.println(res);
    }

    /**
     * 迭代法
     */
    public List<String> solution2(TreeNode root) {
        List<String> res = new ArrayList<>();
        Deque<Object> stack = new LinkedList<>();
        // 节点和路径同时进栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            // 节点和路径同时出战栈
            String path = (String) stack.pop();
            TreeNode cur = (TreeNode) stack.pop();
            if (cur.left == null && cur.right == null) {
                res.add(path);
                continue;
            }
            if (cur.left != null) {
                stack.push(cur.left);
                stack.push(path + "->" + cur.left.val);
            }
            if (cur.right != null) {
                stack.push(cur.right);
                stack.push(path + "->" + cur.right.val);
            }
        }
        return res;
    }

    /**
     * 通俗易懂解法
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) return res;
        traversal(root, path, res);
        return res;
    }

    private void traversal(TreeNode cur, List<Integer> path, List<String> res) {
        // 添加当前节点到path
        path.add(cur.val);
        // 遇到叶子节点结束，即左右节点均为空的时候
        if (cur.left == null && cur.right == null) {
            StringBuilder sbPath = new StringBuilder();
            for (int j = 0; j < path.size() - 1; j++) {
                Integer i = path.get(j);
                sbPath.append(i).append("->");
            }
            sbPath.append(path.get(path.size() - 1));
            res.add(sbPath.toString());
            return;
        }

        // 确定单层逻辑
        if (cur.left != null) {
            traversal(cur.left, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
        if (cur.right != null) {
            traversal(cur.right, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }

    public List<String> solution1(TreeNode root) {
        List<String> res = new ArrayList<>();
        String path = "";
        if (root == null) return res;
        travel(root, path, res);
        return res;
    }

    private void travel(TreeNode cur, String path, List<String> res) {
        path += cur.val;
        if (cur.left == null && cur.right == null) {
            res.add(path);
            return;
        }
        if (cur.left != null) {
            travel(cur.left, path + "->", res);
        }
        if (cur.right != null) {
            travel(cur.right, path + "->", res);
        }
    }
}
