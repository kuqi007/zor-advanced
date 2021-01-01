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

}
