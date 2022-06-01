package com.zor.algorithm.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * <p>
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * <p>
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 * 示例 2：
 * <p>
 * 输入：root = [0]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在 [1, 1000] 范围内
 * Node.val 仅为 0 或 1
 */
public class Leetcode1022 {
    public static void main(String[] args) {
        Leetcode1022 leetcode257 = new Leetcode1022();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 0, 1, 0, 1, 0, 1);
        int i = leetcode257.solution3(root);
        System.out.println(i);
    }

    public int solution3(TreeNode root) {
        dfs1(root, 0);
        return ans;
    }


    private int ans = 0;

    private void dfs1(TreeNode root, int cur) {
        int ncur = cur << 1 | root.val;
        if (root.left != null) dfs1(root.left, ncur);
        if (root.right != null) dfs1(root.right, ncur);
        // 叶子节点，结束逻辑
        if (root.left == null && root.right == null) {
            ans += ncur;
        }
    }


    public int solution2(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int cur) {
        int ans = 0;
        int ncur = cur << 1 | root.val;
        if (root.left != null) ans += dfs(root.left, ncur);
        if (root.right != null) ans += dfs(root.right, ncur);
        return root.left == null && root.right == null ? ncur : ans;
    }

    /**
     * 前序遍历迭代做法
     */
    public int solution1(TreeNode root) {
        Deque<Object> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root.val);
        int sum = 0;
        while (!stack.isEmpty()) {
            int num = (int) stack.pop();
            TreeNode cur = (TreeNode) stack.pop();

            // 叶子节点
            if (cur.left == null && cur.right == null) {
                sum += num;
            }

            if (cur.left != null) {
                stack.push(cur.left);
                stack.push(num * 2 + cur.left.val);
            }

            if (cur.right != null) {
                stack.push(cur.right);
                stack.push(num * 2 + cur.right.val);
            }
        }
        return sum;
    }

    /**
     * 递归
     */
    public int sumRootToLeaf(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger(0);
        traversal(root, path, sum);
        return sum.get();
    }

    private void traversal(TreeNode cur, List<Integer> path, AtomicInteger sum) {
        path.add(cur.val);
        if (cur.left == null && cur.right == null) {
            int x = 0;
            for (Integer i : path) {
                x = x * 2 + i;
            }
            sum.getAndAdd(x);
            return;
        }

        if (cur.left != null) {
            traversal(cur.left, path, sum);
            path.remove(path.size() - 1);
        }
        if (cur.right != null) {
            traversal(cur.right, path, sum);
            path.remove(path.size() - 1);
        }
    }

    private int binToDeci(String s) {
        char[] cs = s.toCharArray();
        int x = 0;
        for (char c : cs) {
            x = x * 2 + (c == '1' ? 1 : 0);
        }
        return x;
    }

    @Test
    public void test() {
        int i = binToDeci("101");
        System.out.println(i);
    }
}
