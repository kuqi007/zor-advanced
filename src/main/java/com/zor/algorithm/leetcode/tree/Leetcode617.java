package com.zor.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 617. 合并二叉树
 * 给你两棵二叉树： root1 和 root2 。
 * <p>
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * <p>
 * 返回合并后的二叉树。
 * <p>
 * 注意: 合并过程必须从两个树的根节点开始。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 * 示例 2：
 * <p>
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -104 <= Node.val <= 104
 * create by tm on 2022/6/10
 */
public class Leetcode617 {

    public static void main(String[] args) {
        Leetcode617 leetcode617 = new Leetcode617();
        TreeNode root1 = TreeNodeUtil.constructBinaryTree(1, 3, 2, 5, null);
        TreeNode root2 = TreeNodeUtil.constructBinaryTree(2, 1, 3, null, 4, null, 7);
        TreeNodeUtil.show(root1);
        TreeNodeUtil.show(root2);
        TreeNode root = leetcode617.solution1(root1, root2);
        TreeNodeUtil.show(root);
    }

    public TreeNode solution1(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        queue.add(root2);
        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            // 因为仅当左右节点不为空时候，才添加到队列，所以此时两个节点肯定不为空
            n1.val = n1.val + n2.val;
            // 不为空，加入队列
            if (n1.left != null && n2.left != null) {
                queue.add(n1.left);
                queue.add(n2.left);
            }
            // 不为空，加入队列
            if (n1.right != null && n2.right != null) {
                queue.add(n1.right);
                queue.add(n2.right);
            }
            // 如果n1左孩子为空，那么直接使用n2左孩子，反之不用处理，还是n1.left
            if (n1.left == null && n2.left != null) {
                n1.left = n2.left;
            }
            // 如果n1右孩子为空，那么直接使用n2右孩子，反之无需处理，还是n1.right
            if (n1.right == null && n2.right != null) {
                n1.right = n2.right;
            }
        }
        return root1;
    }

    /**
     * 递归法：前中后遍历都可以，前序比较好理解
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 终止条件，但凡有一个节点为空，就立即返回另一个
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        // 复用root1这棵树
        root1.val = root1.val + root2.val;
        // root1的左子树
        root1.left = mergeTrees(root1.left, root2.left);
        // root2的右子树
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

}
