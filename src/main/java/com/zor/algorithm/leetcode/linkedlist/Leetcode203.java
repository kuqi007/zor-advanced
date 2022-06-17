package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * create by tm on 2022/6/17
 */
public class Leetcode203 {

    public static void main(String[] args) {
        Leetcode203 leetcode203 = new Leetcode203();
        ListNode listNode = ListNodeUtil.getListNode(7, 7, 7, 7);
        ListNode res = leetcode203.solution1(listNode, 7);
        ListNodeUtil.printList(res);
    }

    /**
     * 递归
     */
    public ListNode dfs(ListNode head, int val) {
        if (head == null) return head;

        head.next = dfs(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    public ListNode solution1(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        while (head != null) {
            ListNode next = head.next;
            if (head.val == val) {
                pre.next = next;
                head.next = null;
            } else {
                pre = head;
            }
            head = next;
        }
        return dummyNode.next;
    }
}
