package com.zor.algorithm.leetcode.tree.traversal;

import com.zor.algorithm.leetcode.tree.TreeNode;
import com.zor.algorithm.leetcode.tree.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的非递归遍历II，统一写法
 * Created by kuqi0 on 2021/6/14
 */
public class TreeTraversalII {

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.constructBinaryTree(5, 4, 6, 1, 2);
        TreeTraversalII traversal = new TreeTraversalII();
        List<Integer> res = traversal.postOrder(root);
        TreeNodeUtil.show(root);
        System.out.println(res);
    }

    /**
     * 前序遍历
     */
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                // pop the node
                stack.pop();
                // right-left-middle
                // right
                if (node.right != null) stack.push(node.right);
                // left
                if (node.left != null) stack.push(node.left);
                // middle
                stack.push(node);
                stack.push(null);
            } else {
                stack.pop();
                TreeNode cur = stack.pop();
                res.add(cur.val);
            }
        }
        return res;
    }

    /**
     * 中序遍历
     */
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                // pop the node, prevent duplicate operation, then add node by the sequence of right-middle-left
                stack.pop();
                // add right
                if (node.right != null) stack.push(node.right);
                // add middle
                stack.push(node);
                stack.push(null);
                // add left
                if (node.left != null) stack.push(node.left);
            } else {
                // when null, add next node to stack
                // pop null node
                stack.pop();
                // get element from stack
                TreeNode cur = stack.pop();
                // add to list
                res.add(cur.val);
            }
        }
        return res;
    }

    /**
     * 后序遍历
     */
    public List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                // pop the node
                stack.pop();
                // middle-left-right
                // middle
                stack.push(node);
                stack.push(null);
                // right
                if (node.right != null) stack.push(node.right);
                // left
                if (node.left != null) stack.push(node.left);
            } else {
                stack.pop();
                TreeNode cur = stack.pop();
                res.add(cur.val);
            }

        }

        return res;
    }

}
