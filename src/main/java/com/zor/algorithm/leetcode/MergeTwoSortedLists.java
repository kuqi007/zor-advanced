package com.zor.algorithm.leetcode;

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
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode listNode1 = ListNodeUtil.getListNode(new int[]{1, 2, 4});
        ListNode listNode2 = ListNodeUtil.getListNode(new int[]{1, 3, 4});
        ListNode listNode = mergeTwoLists(listNode1, listNode2);
        ListNodeUtil.printList(listNode);

    }

    /**
     * TODO 递归。。一看一会，一写就废
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
