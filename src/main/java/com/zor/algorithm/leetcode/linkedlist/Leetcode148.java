package com.zor.algorithm.leetcode.linkedlist;

import com.zor.algorithm.leetcode.linkedlist.base.ListNode;
import com.zor.algorithm.leetcode.linkedlist.base.ListNodeUtil;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by kuqi0 on 2021/6/1
 */
public class Leetcode148 {

    public static void main(String[] args) {
        Leetcode148 leetcode148 = new Leetcode148();
        ListNode listNode1 = ListNodeUtil.getListNode(5, 4, 3, 2, 1, 0, -1);
        ListNode listNode2 = ListNodeUtil.getListNode(-1, 5, 3, 4, 0);
        ListNode res = leetcode148.mergeSort(listNode1);
        ListNodeUtil.printList(res);
    }

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    /**
     * 自顶向下的归并排序
     */
    private ListNode mergeSort(ListNode head) {
        if (head.next == null) return head;
        ListNode p = head;
        ListNode q = head;
        ListNode pre = null;
        while (p != null && p.next != null) {
            pre = q;
            p = p.next.next;
            q = q.next;
        }
        pre.next = null;
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(q);
        return merge(l1, l2);
    }


    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode cur = dummyNode;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                cur.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                cur.next = l1;
                l1 = l1.next;
            } else if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummyNode.next;
    }


    /**
     * // TODO: 2021/6/1
     * 链表冒泡排序
     */
    public ListNode sort(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = head;
        ListNode pre = dummyNode;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            ListNode next2 = next.next;
            if (next.val < cur.val) {
                pre.next = next;
                next.next = cur;
                cur.next = next2;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummyNode.next;
    }

}
