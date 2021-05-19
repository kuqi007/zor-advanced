package com.zor.algorithm.leetcode.tree.traversal;

import com.zor.algorithm.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/che-di-chi-tou-er-cha-shu-de-qian-zhong-hou-xu-d-2/
 * 树的中序遍历
 * Created by kuqi0 on 2021/5/16
 */
public class InorderTraversal {


    public List<Integer> solution1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();// 将该节点弹出，避免重复操作，下面再将 右中左 节点添加到栈中
                if (node.right != null) {
                    stack.push(node.right);// 添加右节点（空节点不如栈）
                }
                stack.push(node);//添加中节点
                stack.push(null);// 中节点访问过，但是还没有处理，加入空节点作为标记

                if (node.left != null) {
                    stack.push(node.left);// 添加左节点（空节点不入栈）
                }
            } else {// 只有遇到空节点的时候，才将下一个节点放入结果集
                stack.pop();// 将空节点弹出
                node = stack.peek();// 重新取出栈中元素
                stack.pop();
                res.add(node.val);
            }
        }
        return res;
    }

}
