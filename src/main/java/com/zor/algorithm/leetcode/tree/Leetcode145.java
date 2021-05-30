package com.zor.algorithm.leetcode.tree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * Created by kuqi0 on 2021/5/15
 */
public class Leetcode145 {

    /**
     * 本质上也是中右左的逆转
     */
    public List<Integer> solution3(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                res.addFirst(cur.val);
                cur = cur.right;
            } else {
                cur = stack.pop().left;
            }
        }
        return res;
    }


    /**
     * 中右左熟顺序的翻转，使用LinkedList性能较好
     */
    public List<Integer> solution2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.addFirst(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }


    /**
     * 迭代后序遍历，中右左顺序，然后逆转
     */
    public List<Integer> solution1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            // 数组添加到头部性能比较低
            res.add(0, node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.val);
    }
}
