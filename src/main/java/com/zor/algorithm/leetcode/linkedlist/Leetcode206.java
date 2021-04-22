package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * No.206
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 */
public class Leetcode206 {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4, 5);
        ListNodeUtil.printList(solution1(listNode));
    }

    public static ListNode solution1(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            // 保存一下原来next的指针，防止指针丢失
            ListNode next = cur.next;
            // 只要将当前节点指向前节点即可，无需关心next节点
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode solution2(ListNode head) {
        //递归执行顺序是先执行到最后一个节点，将最后的节点反转之后依次往回走
        //4->3->2->1, head是当前节点，newHead是最后一个节点

        /* 递归终止条件：
         * 1. head==null链表为空，不需要反转
         * 2. head.next==null只剩一个节点的时候，也不需要反转
         */
        if (head == null || head.next == null) {
            return head;
        }
        // 递
        ListNode newHead = solution2(head.next);
        // 归
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    /**
     * 迭代写法
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 当cur=null时，循环退出，所以链表的头是pre
        return pre;
    }

    /**
     * 递归写法
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
