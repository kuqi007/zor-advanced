package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * 示例 2:
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数量在 [0, 212 - 1] 范围内
 * -1000 <= node.val <= 1000
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * Created by kuqi0 on 2022/6/5
 */
public class Leetcode116 {

    /**
     * todo 递归
     */
    public Node connect(Node root) {
        if (root == null) return root;

        dfs(root);

        return root;
    }

    /**
     * 使用n-1去操作n层，当前层实际上已经连接好了
     */
    private void dfs(Node root) {
        // 已经是叶子节点了，退出
        if (root.left == null || root.right == null) return;
        // 左边节点指向右边节点
        root.left.next = root.right;
        // 如果不是最右侧的节点
        if (root.next != null) {
            root.right.next = root.next.left;
        }

        dfs(root.left);
        dfs(root.right);

    }

    /**
     * 层序遍历，O(N)空间，不满足题意
     */
    public Node levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node cur = null;
            Node preNode = null;
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                if (i == 0) {
                    preNode = cur;
                } else {
                    preNode.next = cur;
                    preNode = cur;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }

    /**
     * 这个是O(n)空间，其实不符合要求
     * 从右往左的层序遍历，无需记录指针
     */
    public Node bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node next = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                node.next = next;
                next = node;
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
