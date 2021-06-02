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
        ListNode listNode1 = ListNodeUtil.getListNode(5, 4, 3, 2, 1, 0);
        ListNode listNode2 = ListNodeUtil.getListNode(-1, 5, 3, 4, 0);
        ListNode res = leetcode148.mergeSort(listNode1);
        ListNodeUtil.printList(res);
    }

    /**
     * 如果slow = fast = head,那么在链表只剩两个节点的时候有以下执行顺序：(首节点->尾节点->null)
     * 0. 执行查找中点的while循环
     * 1. 得出：slow = 尾节点，fast = null
     * 2. mid = slow.next;
     * slow.next = null;
     * //第2步为断链,需要注意的是,slow的next原本就是null(由第1步得出)
     * //也就是说,断链操作根本没有生效,链表结构依然是:首节点->尾节点->null
     * 3. 进入递归,由于链表结构没变,所以会继续进行查找中点、断链操作,重复以上步骤,导致栈溢出
     * -----------------------------------------------------------
     * 如果slow = head,fast = head.next,那么在链表只剩两个节点的时候有以下执行顺序：(首节点->尾节点->null)
     * 0. 执行查找中点的while循环
     * 1. 得出：slow = 首节点，fast = 尾节点
     * 2. mid = slow.next;
     * slow.next = null;
     * //这里的断链操作生效了,此时链表结构为：首节点->null,尾节点->null
     * 3. 进入递归,重复以上步骤,此时的链表已经分解成两个节点,所以不会查找中点及断链,而是直接return。
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 这里需要注意
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }


    /**
     * 自顶向下的归并排序
     */
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(slow);
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
