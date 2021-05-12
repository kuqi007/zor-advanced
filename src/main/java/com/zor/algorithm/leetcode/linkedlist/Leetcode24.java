package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * Created by kuqi0 on 2021/5/11
 */
public class Leetcode24 {

    public static void main(String[] args) {
        Leetcode24 leetcode24 = new Leetcode24();
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4);
        ListNode res = leetcode24.swapPairs(listNode);
        ListNodeUtil.printList(res);

    }


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }


    public ListNode solution1(ListNode head) {

        ListNode dummyNode = new ListNode(-1, head);
        ListNode temp = dummyNode;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }

        return dummyNode.next;


    }


}
