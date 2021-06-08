package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class Leetcode86 {
    public static void main(String[] args) {
        Leetcode86 leetcode86 = new Leetcode86();
        ListNode list = ListNodeUtil.getListNode(2, 1);
        ListNode partition = leetcode86.partition(list, 2);

        ListNodeUtil.printList(partition);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1), large = new ListNode(-1);
        ListNode smallCur = small, largeCur = large;
        while (head != null) {
            if (head.val < x) {
                smallCur.next = head;
                smallCur = smallCur.next;
            } else {
                largeCur.next = head;
                largeCur = largeCur.next;
            }
            head = head.next;
        }
        largeCur.next = null;
        smallCur.next = large.next;
        return small.next;
    }


    /**
     * 我的做法
     */
    public ListNode solution1(ListNode head, int x) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        ListNode first = dummyNode;
        while (head != null) {
            ListNode n2 = head.next;
            if (head.val < x) {
                if (first.next != head) {
                    ListNode n1 = first.next;
                    first.next = head;
                    head.next = n1;
                    pre.next = n2;
                }
                first = head;
            } else {
                pre = head;
            }
            head = n2;
        }
        return dummyNode.next;
    }

}
