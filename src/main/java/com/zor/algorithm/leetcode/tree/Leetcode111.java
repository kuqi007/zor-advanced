package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 * Created by kuqi0 on 2021/5/23
 */
public class Leetcode111 {

    public static void main(String[] args) {
        Leetcode111 leetcode111 = new Leetcode111();
        TreeNode root = TreeNodeUtil.constructBinaryTree(2, null, 3, null, 4, null, 5, null, 6);
        TreeNodeUtil.show(root);
        int i = leetcode111.solution1(root);
        System.out.println(i);
    }


    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left); // 左
        int r = dfs(root.right);// 右
                                // 中
        // 当一个左子树为空，右子树不为空，取右子树高度+1
        if (root.left == null && root.right != null) {
            return r + 1;
        }
        // 当一个左子树不为空，右子树为空，取左子树高度+1
        if (root.left != null && root.right == null) {
            return l + 1;
        }

        // 都不为空，取两者最小值+1
        return Math.min(l, r) + 1;

    }

    /**
     * 层序遍历
     */
    public int solution2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth + 1;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }


    /**
     * 通俗易懂
     * <a href="https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/111-er-cha-shu-de-zui-xiao-shen-du-di-gu-ztum/">https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/111-er-cha-shu-de-zui-xiao-shen-du-di-gu-ztum/</a>
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 当一个左子树为空，右不为空，这时并不是最低点
        if (root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        }
        // 当一个右子树为空，左不为空，这时并不是最低点
        if (root.right == null && root.left != null) {
            return 1 + minDepth(root.left);
        }

        // 左右节点均不为空，则取一个最小的深度
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));

    }

    public int solution1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        // 保存左右子树最小深度
        int res = Integer.MAX_VALUE;
        if (root.left != null) {
            res = Math.min(res, solution1(root.left));
        }
        if (root.right != null) {
            res = Math.min(res, solution1(root.right));
        }
        return res + 1;
    }

    /**
     * 层序遍历，当我们找到第一个叶子节点时候，这就是最小深度
     * 广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小。
     */
    public int bfs(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                size--;
            }

        }
        return depth;
    }


}
