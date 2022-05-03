package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 * Created by kuqi0 on 2021/5/11
 */
public class Leetcode24 {

    public static void main(String[] args) {
        Leetcode24 leetcode24 = new Leetcode24();
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4);
        ListNode res = leetcode24.swapPairs1(listNode);
        ListNodeUtil.printList(res);

    }

    public ListNode solution0(ListNode head) {
        return reverseKGroup(head, 2);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        ListNode end = dummyNode;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 如果end为空，表示不足k个，不翻转
            if (end == null) break;
            ListNode start = pre.next;
            // 先保存下一组的头结点
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(pre.next);
            // 连接下一组
            start.next = next;

            pre = start;
            end = start;

        }
        return dummyNode.next;
    }

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


    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs1(next.next);
        next.next = head;
        return next;
    }

    public ListNode swapPairs(ListNode head) {
        // 递归终止条件，当节点为空或者只有一个节点时无需交换
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        // 这里我们只需要处理三个节点，也就是head、next以及已经处理完的链表部分
        // 把head指向已经处理好的链表部分
        head.next = swapPairs(next.next);
        // next指向head
        next.next = head;
        // 返回新的头结点
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

    /**
     * 迭代做法
     * 2022年5月3日12:08:52
     */
    public ListNode swapPairs2(ListNode head) {
        // 创建哑结点
        ListNode dummyNode = new ListNode(0, head);
        // 三个节点，temp，p1和p2，处理完本次交换后，temp指针往后走即可
        ListNode temp = dummyNode;
        while (temp.next != null && temp.next.next != null) {
            ListNode p1 = temp.next;
            ListNode p2 = temp.next.next;
            temp.next = p2;
            p1.next = p2.next;
            p2.next = p1;
            temp = p1;
        }
        return dummyNode.next;
    }


}
