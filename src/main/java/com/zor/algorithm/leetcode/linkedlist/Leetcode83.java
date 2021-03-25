package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 *
 * @author zqq
 * @date 2021/3/25
 */
public class Leetcode83 {

    public static void main(String[] args) {

        ListNode listNode = ListNodeUtil.getListNode(1, 1, 1);
        ListNodeUtil.printList(deleteDuplicates(listNode));

    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                // 仅当下个元素不等于当前才往下走
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * TODO 递归写法 回头再看
     */
    public static ListNode solution0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = solution0(head.next);
        if (head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }

    /**
     * 想复杂了
     */
    public static ListNode solution1(ListNode head) {
        ListNode dummyNode = head;
        ListNode pre = null;
        while (head != null) {
            if (pre != null && pre.val == head.val) {
                pre.next = head.next;
            } else {
                // 如果是不相等的情况，pre往下走
                pre = head;
            }
            head = head.next;
        }
        return dummyNode;
    }
}
