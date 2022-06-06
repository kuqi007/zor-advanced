package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
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
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 * create by tm on 2022/6/6
 */
public class Leetcode117 {

    public Node connect(Node root) {

        dfs(root);

        return root;
    }


    private void dfs(Node root) {
        if (root == null) return;

        // 叶子节点，则无需操作
        if (root.left == null && root.right == null) return;

        // 如果左右节点均不为空，左节点指向右节点
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }

        Node leftNode = root.right;
        if (root.right == null) {
            leftNode = root.left;
        }
        leftNode.next = getNextNode(root.next);
        // todo 这边有点迷惑
        // 必须先操作右子树，使右边全部连接完毕，否则会出现左边节点无法获得正确的next
        dfs(root.right);
        dfs(root.left);
    }

    private Node getNextNode(Node root) {
        if (root == null) return null;
        if (root.left != null) return root.left;
        if (root.right != null) return root.right;
        // 这个地方需要注意，如果先递归左子树，会出现右子树节点之间还未连接的情况，那么就拿不到下一个节点了
        if (root.next != null) return getNextNode(root.next);
        return null;
    }


    public Node leverOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node next = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                cur.next = next;
                next = cur;

                if (cur.right != null) queue.add(cur.right);
                if (cur.left != null) queue.add(cur.left);
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

    ;
}
