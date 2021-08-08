package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 * <p>
 * <p>
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 * <p>
 * <p>
 * 插入排序算法：
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * Created by kuqi0 on 2021/8/6
 */
public class Leetcode147 {

    public static void main(String[] args) {
        Leetcode147 leetcode147 = new Leetcode147();
        ListNode listNode = ListNodeUtil.getListNode(4, 2, 1, 3);

        ListNode listNode1 = leetcode147.solution1(listNode);
        ListNodeUtil.printList(listNode1);


    }


    /**
     * https://leetcode-cn.com/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
     */
    public ListNode solution1(ListNode head) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode lastSorted = head, cur = head.next;
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode pre = dummyNode;
                while (pre.next.val <= cur.val) {
                    pre = pre.next;
                }
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummyNode.next;
    }


    public ListNode insertionSortList(ListNode head) {
        ListNode cur = head.next, pre = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val < pre.val) {
                pre.next = next;
                cur.next = null;
                head = insertion(head, cur);
            } else {
                pre = cur;
            }
            cur = next;
        }
        return head;
    }

    /**
     * 将节点找到位置插入
     *
     * @param head head
     * @param node node
     */
    private ListNode insertion(ListNode head, ListNode node) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode cur = dummyNode.next, pre = dummyNode;
        while (cur != null) {
            if (node.val < cur.val) {
                pre.next = node;
                node.next = cur;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummyNode.next;
    }
}
