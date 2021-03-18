package com.zor.algorithm.leetcode.linkedlist.base;

/**
 * Created by kuqi0 on 2020/12/28
 */
public class ListNodeUtil {

    /**
     * 获取一个环形链表
     *
     * @param nodeArr node数组
     * @param pos     环的位置
     * @return ListNode
     */
    public static ListNode getCycledLinkedList(int[] nodeArr, int pos) {
        if (pos < 0 || pos > nodeArr.length - 1) {
            return null;
        }

        // 第一个入环节点
        ListNode ptrNode = new ListNode((nodeArr[pos]));
        // 傀儡节点
        ListNode dummyNode = new ListNode(0);
        ListNode p = dummyNode;
        for (int i = 0; i < nodeArr.length; i++) {
            if (i == pos) {
                p.next = ptrNode;
            } else {
                p.next = new ListNode(nodeArr[i]);
            }
            p = p.next;
        }

        // 入环
        p.next = ptrNode;

        return dummyNode.next;
    }

    /**
     * 输入一个数组，获得一个链表
     */
    public static ListNode getListNode(Integer... nodeArr) {
        if (nodeArr == null || nodeArr.length == 0) {
            return null;
        }
        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;
        for (int value : nodeArr) {
            p.next = new ListNode(value);
            p = p.next;
        }

        return dummyNode.next;
    }

    public static void printList(ListNode head) {
        StringBuilder result = new StringBuilder();
        while (head != null) {
            result.append(head.val);
            if (head.next != null) {
                result.append(" -> ");
            }
            head = head.next;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        ListNode listNode = getListNode(1, 1, 2, 3, 4, 4);
        printList(listNode);
    }

}
