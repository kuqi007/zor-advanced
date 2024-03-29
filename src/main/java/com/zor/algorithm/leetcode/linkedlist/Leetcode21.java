package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * Created by kuqi0 on 2021/1/3
 */
public class Leetcode21 {
    public static void main(String[] args) {
        ListNode listNode1 = ListNodeUtil.getListNode(1, 2, 4);
        ListNode listNode2 = ListNodeUtil.getListNode(1, 3, 4);
        ListNode listNode = solution3(listNode1, listNode2);
        ListNodeUtil.printList(listNode);

    }

    /**
     *  递归。。一看就会，一写就废
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            // 如果l1已经到尽头，把此时没有结束的l2返回
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            // 拆成子问题，把l1的下一个节点指向l1.next与l2的合并的结果
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    /**
     * 分支合并
     */
    public static ListNode solution4(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode head = dummyNode;
        while (l1 != null || l2 != null) {
            if (l1 == null || (l2 != null && l1.val > l2.val)) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    /**
     * 四个分支
     */
    public static ListNode solution3(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode head = dummyNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                head.next = l1;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        return dummyNode.next;
    }

    public static ListNode solution1(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode head = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        head.next = l1 != null ? l1 : l2;
        return dummyNode.next;
    }

    /**
     * 迭代做法
     */
    public static ListNode solution2(ListNode l1, ListNode l2) {

        ListNode dummyNode = new ListNode(-1);

        ListNode prev = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 != null ? l1 : l2;

        return dummyNode.next;
    }
}
