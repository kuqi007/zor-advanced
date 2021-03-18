package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author zqq
 * @date 2021/3/18
 */
public class Leetcode92 {
    public static void main(String[] args) {
        ListNode listNode = ListNodeUtil.getListNode(1, 2, 3, 4, 5);
        ListNodeUtil.printList(solution2(listNode, 2, 4));
    }

    public static ListNode solution1(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        // 第一步：从虚拟节点走left-1步，来到left节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 第二步：从pre再走right-left+1步，来到right节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第三步：切断一个子链表（截取链表）
        ListNode leftNode = pre.next;
        // 保存后置节点
        ListNode succ = rightNode.next;
        // 切断链接
        pre.next = null;
        rightNode.next = null;

        // 第四步：反转链表的子区间，此时leftNode成为子链表的尾节点
        reverseList(leftNode);
        // 第五步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = succ;
        return dummyNode.next;
    }

    private static void reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


    /**
     * 头插法
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/java-shuang-zhi-zhen-tou-cha-fa-by-mu-yi-cheng-zho/
     */
    public static ListNode solution2(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        for (int i = 0; i < left - 1; i++) {
            g = g.next;
            p = p.next;
        }

        for (int i = 0; i < right - left; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;
            // g.next会一直变化
            removed.next = g.next;
            g.next = removed;
        }

        return dummyHead.next;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 傀儡节点，不需要判断各种边界情况
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;

        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode leftNode = pre.next;

        // 反转子链表，总共操作right-left+1步
        ListNode reversePre = pre.next;
        ListNode reverseCur = leftNode;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = reverseCur.next;
            reverseCur.next = reversePre;
            reversePre = reverseCur;
            reverseCur = next;
        }
        // 反转之后reversePre即子链表的头
        // 此时leftNode是子链表的尾结点，reverseCur是原来的子链表最后节点的下一个节点
        pre.next = reversePre;
        leftNode.next = reverseCur;

        return dummyNode.next;
    }


}
