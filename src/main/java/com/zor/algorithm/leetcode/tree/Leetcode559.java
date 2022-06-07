package com.zor.algorithm.leetcode.tree;

import java.util.List;

/**
 * 559. N 叉树的最大深度
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：3
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：5
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树的深度不会超过 1000 。
 * 树的节点数目位于 [0, 104] 之间。
 * create by tm on 2022/6/7
 */
public class Leetcode559 {
    public static void main(String[] args) {

    }


    public int maxDepth(Node root) {
        if (root == null) return 0;
        int maxDepth = 0;
        for (int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            maxDepth = Math.max(maxDepth(child), maxDepth);
        }

        return maxDepth + 1;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
