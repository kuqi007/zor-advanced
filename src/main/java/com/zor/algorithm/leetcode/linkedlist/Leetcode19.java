package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * Created by zhuqiqi03 on 2021/4/2
 */
public class Leetcode19 {
    public static void main(String[] args) {

        ListNode listNode = ListNodeUtil.getListNode(1);
        ListNode listNode1 = solution1(listNode, 1);
        ListNodeUtil.printList(listNode1);

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyNode = new ListNode(0, head);

        int length = getLength(head);

        if (length < n) return head;

        ListNode cur = dummyNode;
        // 下标从1开始，到size-n
        for (int i = 1; i <= length - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummyNode.next;
    }

    private static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

    /**
     * 使用栈
     */
    public static ListNode solution1(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummyNode;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        return dummyNode.next;
    }

    /**
     * 双指针做法
     */
    public static ListNode solution2(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummyNode;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyNode.next;
    }
}
