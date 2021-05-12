package com.zor.algorithm.interview.online.didi;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 成对翻转链表
 * 给定一个单向链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * 滴滴面试
 * Created by kuqi0 on 2021/5/11
 */
public class MyTest {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4);
        ListNode listNode1 = reverseNode(listNode);
        ListNodeUtil.printList(listNode1);
    }

    public static ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = reverseNode(next.next);
        next.next = head;
        return next;
    }


}
