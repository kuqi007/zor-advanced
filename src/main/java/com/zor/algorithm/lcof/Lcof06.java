package com.zor.algorithm.lcof;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 *
 * @author zhuqiqi03
 * @date 2021/6/10
 */
public class Lcof06 {

    public int[] reversePrint(ListNode head) {
        ListNode cur = head, pre = null;
        int size = 0;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            size++;
        }

        int[] res = new int[size];
        int i = 0;
        while (pre != null) {
            res[i++] = pre.val;
            pre = pre.next;
        }
        return res;

    }
}
