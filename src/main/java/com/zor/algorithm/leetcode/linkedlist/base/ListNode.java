package com.zor.algorithm.leetcode.linkedlist.base;

public class ListNode {
    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    //@Override
    //public boolean equals(Object o) {
    //    if (this == o) return true;
    //    if (o == null || getClass() != o.getClass()) return false;
    //    ListNode listNode = (ListNode) o;
    //    return val == listNode.val;
    //}
    //
    //@Override
    //public int hashCode() {
    //    return Integer.hashCode(val);
    //}
}
