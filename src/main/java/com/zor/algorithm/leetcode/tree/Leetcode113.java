package com.zor.algorithm.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * Created by kuqi0 on 2021/5/23
 */
public class Leetcode113 {

    public static void main(String[] args) {
        Leetcode113 leetcode113 = new Leetcode113();
        TreeNode root = TreeNodeUtil.constructBinaryTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        TreeNodeUtil.printTree(root);
        List<List<Integer>> res = leetcode113.pathSum(root, 22);
        System.out.println(res);
    }

    /**
     * 递归写法，这个写法比较容易理解
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> path = new ArrayList<>();
        pathSum(root, targetSum, path, res);
        return res;
    }

    private void pathSum(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
        int curVal = root.val;
        path.add(curVal);
        // 遇到叶子节点
        if (root.left == null && root.right == null && targetSum == curVal) {
            res.add(new ArrayList<>(path));
        }

        if (root.left != null) {
            pathSum(root.left, targetSum - curVal, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            pathSum(root.right, targetSum - curVal, path, res);
            // 回溯
            path.remove(path.size() - 1);
        }
    }

    /**
     * 比较愚蠢的做做法，使用一个List保存已经遍历过的路径，但是比较好理解
     */
    public List<List<Integer>> solution1(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        Deque<List<Integer>> path = new LinkedList<>();
        stack.push(root);
        path.push(Collections.singletonList(root.val));
        while (!stack.isEmpty()) {
            List<Integer> curPath = path.pop();
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                int sum = 0;
                for (Integer i : curPath) {
                    sum += i;
                }
                if (targetSum == sum) {
                    res.add(curPath);
                }
            }

            if (cur.right != null) {
                stack.push(cur.right);
                List<Integer> tmp = new ArrayList<>(curPath);
                tmp.add(cur.right.val);
                path.push(tmp);
            }

            if (cur.left != null) {
                stack.push(cur.left);
                List<Integer> tmp = new ArrayList<>(curPath);
                tmp.add(cur.left.val);
                path.push(tmp);
            }
        }
        return res;
    }

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    /**
     * 2021-05-23日没看懂
     * 2022-06-02看懂了(*^▽^*)
     */
    public List<List<Integer>> solution0(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        path.pollLast();
    }
}
