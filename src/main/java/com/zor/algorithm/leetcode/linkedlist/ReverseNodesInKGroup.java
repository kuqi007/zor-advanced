package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * k个一组翻转链表，不足k个也翻转
 * Created by kuqi0 on 2021/5/15
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4, 5, 6, 7, 8);
        ListNode ans = reverseNodesInKGroup.reverseKGroup(listNode, 3);

        ListNodeUtil.printList(ans);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        ListNode end = dummyNode;

        while (end.next != null) {
            // 不足k个也翻转
            for (int i = 0; i < k && end.next != null; i++) {
                end = end.next;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            // 将链表拆开，进行反转，否则反转的将不止本次链表
            end.next = null;
            pre.next = reverse(start);
            start.next = next;

            pre = start;
            end = start;
        }
        return dummyNode.next;
    }


    /**
     * 标准链表翻转
     */
    private ListNode reverse(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
