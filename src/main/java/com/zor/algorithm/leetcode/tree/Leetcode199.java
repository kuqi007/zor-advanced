package com.zor.algorithm.leetcode.tree;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * <p>
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 * <p>
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 * <p>
 * 输入: []
 * 输出: []
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 * Created by kuqi0 on 2022/6/5
 */
public class Leetcode199 {

    public static void main(String[] args) {
        Leetcode199 leetcode = new Leetcode199();
        TreeNode root = TreeNodeUtil.constructBinaryTree();

        List<Integer> res = leetcode.rightSideView(root);
        System.out.println(res);
    }

    public List<Integer> solution1(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    // res传入的形式，递归不需要返回值，这样简化递归函数的写法
    private List<Integer> res = new ArrayList<>();

    /**
     * 中右左的访问顺序，保证右侧节点是最先访问到的，比如[1,2,3,null,5,null,4]，这棵树，中右左遍历为 1->3->4->2->5,截取当前深度的前h个元素
     */
    public void dfs(TreeNode root, int depth) {
        // 终止条件
        if (root == null) return;
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {
            // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }


    /**
     * 层序遍历-easy
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) {
                    res.add(cur.val);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
}
