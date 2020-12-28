package com.zor.algorithm.leetcode.base;

import java.util.LinkedList;

/**
 * Created by kuqi0 on 2020/12/28
 */
public class ListNodeUtil {

    public static ListNode getCycledLinkedList(int[] nodeArr, int pos) {
        if (pos < 0 || pos > nodeArr.length - 1) {
            return null;
        }
        ListNode head = new ListNode(nodeArr[0]);
        ListNode p = head;
        for (int i = 1; i < nodeArr.length; i++) {
            p.next = new ListNode(nodeArr[i]);
            p = p.next;
        }

        p.next = new ListNode(nodeArr[pos]);

        return head;
    }

}
