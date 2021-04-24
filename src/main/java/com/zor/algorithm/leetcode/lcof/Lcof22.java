package com.zor.algorithm.leetcode.lcof;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * <p>
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * <p>
 * 返回链表 4->5.
 * Created by kuqi0 on 2021/4/24
 */
public class Lcof22 {
    public static void main(String[] args) {

    }

    /**
     * 这一题跟主站19题类似，使用双指针做法，
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < k; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second;

    }
}
