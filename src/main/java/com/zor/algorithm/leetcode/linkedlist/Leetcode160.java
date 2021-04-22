package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * <p>
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * Created by kuqi0 on 2021/4/22
 */
public class Leetcode160 {

    public static void main(String[] args) {


    }

    /**
     * 方法3初看很难理解，但是细想就会发现很简单很巧妙 A和B两个链表长度可能不同，但是A+B和B+A的长度是相同的，
     * 所以遍历A+B和遍历B+A一定是同时结束。 如果A,B相交的话A和B有一段尾巴是相同的，
     * 所以两个遍历的指针一定会同时到达交点 如果A,B不相交的话两个指针就会同时到达A+B（B+A）的尾节点(同时为null)
     */
    public static ListNode solution2(ListNode headA, ListNode headB) {
        // 特判
        if (headA == null || headB == null) {
            return null;
        }
        ListNode head1 = headA;
        ListNode head2 = headB;
        while (head1 != head2) {
            if (head1 != null) {
                head1 = head1.next;
            } else {
                head1 = headB;
            }

            if (head2 != null) {
                head2 = head2.next;
            } else {
                head2 = headA;
            }
        }

        return head1;
    }

    /**
     * 哈希表
     */
    public static ListNode solution1(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;

    }

    /**
     * 丑陋的暴力解法
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode cur = headB;
            while (cur != null) {
                if (headA == cur) return headA;
                cur = cur.next;
            }
            headA = headA.next;
        }
        return null;
    }
}
