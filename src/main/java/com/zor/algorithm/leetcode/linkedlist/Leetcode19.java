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

        ListNode listNode = ListNodeUtil.getListNode(1, 2);
        ListNode listNode1 = solution2(listNode, 2);
        ListNodeUtil.printList(listNode1);

    }

    /**
     * 1. 使用dummyNode，方便操作，不然要判断特殊情况
     * 2. 快慢指针，均指向快指针dummyNode，fast走n+1步，那么最后slow会走到要删除节点的前驱节点，即倒数n+1个节点（slow走了size-n-1步）
     */
    public static ListNode solution2(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1, head);
        // 快慢指针均指向dummyNode
        ListNode fast = dummyNode, slow = dummyNode;
        // fast走n+1步，这样slow最后会走到要删除节点的前驱节点
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow是要删除节点的前驱节点
        slow.next = slow.next.next;
        return dummyNode.next;
    }

    /**
     * 双指针做法
     */
    public static ListNode bestSolution(ListNode head, int n) {
        // dummyNode在删除链表节点情况下，非常好用，不用去判NPE了
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


}
