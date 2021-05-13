package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 *
 * @author zqq
 * @date 2021/3/25
 */
public class Leetcode82 {

    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 3, 4, 4, 5);
        ListNodeUtil.printList(deleteDuplicates(listNode));
    }

    /**
     * 一次遍历
     */
    public static ListNode solution0(ListNode head) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode cur = dummyNode;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummyNode.next;
    }

    /**
     * TODO 递归没看懂
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 没有节点或者只有一个节点，必然没有重复元素
        if (head == null || head.next == null) {
            return head;
        }
        // 当前节点和下一个节点，值不同，则head的值是需要保留的，对head.next继续递归
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
        } else {
            // 当前节点与下一个节点的值重复了，重复的值都不能要。
            // 一直往下找，找到不重复的节点。返回对不重复节点的递归结果
            ListNode move = head.next;
            while (move != null && head.val == move.val) {
                move = move.next;
            }
            return deleteDuplicates(move);
        }
        return head;
    }

    /**
     * 利用计数，两次遍历
     */
    public static ListNode solution2(ListNode head) {
        Map<Integer, Integer> counter = new HashMap<>();
        ListNode temp = head;
        while (temp != null) {
            counter.merge(temp.val, 1, Integer::sum);
            temp = temp.next;
        }
        // 第一种做法
        //ListNode dummyNode = new ListNode(0, head);
        //
        //ListNode cur = dummyNode;
        //while (cur.next != null) {
        //    if (counter.get(cur.next.val) > 1) {
        //        cur.next = cur.next.next;
        //    } else {
        //        cur = cur.next;
        //    }
        //}
        // 第二种做法
        ListNode dummyNode = new ListNode(0);
        ListNode res = dummyNode;
        while (head != null) {
            if (counter.get(head.val) == 1) {
                dummyNode.next = new ListNode(head.val);
                dummyNode = dummyNode.next;
            }
            head = head.next;
        }
        return res.next;
    }


}
