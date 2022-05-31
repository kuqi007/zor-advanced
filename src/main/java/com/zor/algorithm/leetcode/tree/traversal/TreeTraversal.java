package com.zor.algorithm.leetcode.tree.traversal;

import com.zor.algorithm.leetcode.tree.TreeNode;
import com.zor.algorithm.leetcode.tree.TreeNodeUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的非递归遍历，普通写法
 * Created by kuqi0 on 2021/6/14
 */
public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.constructBinaryTree(5, 4, 6, 1, 2);
        TreeTraversal traversal = new TreeTraversal();
        List<Integer> res = traversal.preOrder(root);
        TreeNodeUtil.show(root);
        System.out.println(res);
    }

    /**
     * 前序遍历，最简单
     */
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.poll();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
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
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 指针来访问节点，访问到最底层
            if (cur != null) {
                // 将访问的节点放进栈
                stack.push(cur);
                // 左
                cur = cur.left;
            } else {
                // 从栈里探出的数据就是要处理的数据（放进res里的数据）
                TreeNode pop = stack.pop();
                // 中
                res.add(pop.val);
                // 右
                cur = pop.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历
     */
    public List<Integer> postOrder(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        // 后序遍历是左右中，反过来就是中右左，将前序遍历入栈的顺序调换一下，然后res revert一下就可以了
        Deque<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return res;
    }

}
