package com.zor.algorithm.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * <p>
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 3     2
 * / \     \
 * 5   3     9
 * <p>
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * /
 * 3
 * / \
 * 5   3
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /
 * 5
 * <p>
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 3   2
 * /     \
 * 5       9
 * /         \
 * 6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 * create by tm on 2022/6/14
 */
public class Leetcode662 {

    public static void main(String[] args) {
        Leetcode662 leetcode = new Leetcode662();
        TreeNode root = TreeNodeUtil.constructBinaryTree(1, 3, 2, 5, null, null, 9, 6, null, null, null, null, null, 7, null);
        System.out.println(leetcode.bfs(root));
    }

    public int solution1(TreeNode root) {

        dfs(root, 1, 1, new ArrayList<>());
        return max;

    }

    private int max = Integer.MIN_VALUE;

    private void dfs(TreeNode root, int level, int index, List<Integer> left) {
        if (root == null) return;
        // left保留每一层最左边节点下标，如果left大小还小于level，说明是最左边节点
        if (left.size() < level) left.add(index);

        // 比较当前节点和最左边节点差值
        // level从1开始，所以需要-1
        max = Math.max(max, index - left.get(level - 1) + 1);

        dfs(root.left, level + 1, index * 2, left);
        dfs(root.right, level + 1, index * 2 + 1, left);
    }


    /**
     * 层序遍历，记录position
     */
    public int bfs(TreeNode root) {
        int res = 0;
        Deque<CustomNode> deque = new ArrayDeque<>();
        // 节点下标从0开始
        if (root != null) deque.add(new CustomNode(root, 0));
        while (!deque.isEmpty()) {
            // 拿到第一个节点
            CustomNode first = deque.peekFirst();
            // 拿到最后一个节点
            CustomNode last = deque.peekLast();
            // 位置相减
            res = Math.max(res, last.position - first.position + 1);
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                CustomNode cur = deque.poll();
                if (cur.node.left != null) {
                    deque.add(new CustomNode(cur.node.left, cur.position * 2 + 1));
                }
                if (cur.node.right != null) {
                    deque.add(new CustomNode(cur.node.right, cur.position * 2 + 2));
                }
            }
        }
        return res;
    }

    class CustomNode {
        TreeNode node;
        Integer position;

        public CustomNode(TreeNode node, Integer position) {
            this.node = node;
            this.position = position;
        }
    }
}
