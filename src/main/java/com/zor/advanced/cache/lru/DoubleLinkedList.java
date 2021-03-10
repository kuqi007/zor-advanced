package com.zor.advanced.cache.lru;

/**
 * 参考LinkedList的双向链表的不完全实现
 *
 * @see java.util.LinkedList
 * Created by kuqi0 on 2021/3/9
 */
public class DoubleLinkedList implements DoubleList {

    private Node first;
    private Node last;

    private int size = 0;


    /**
     * 在链表头部添加节点x，时间O(1)
     */
    public void addFirst(Node x) {
        final Node f = first;
        first = x;
        if (f == null) {
            // 如果原来的头结点为空，那么尾结点也肯定为空，让头尾节点都为新节点
            last = x;
        } else {
            f.prev = x;
        }
        size++;
    }

    /**
     * 删除链表中的 x 节点（x ⼀定存在）
     * 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
     */
    public void remove(Node x) {
        final Node pre = x.prev;
        final Node next = x.next;
        // 如果前置结点为空，表示当前结点是第一个结点
        if (pre == null) {
            first = next;
        } else {
            pre.next = next;
            x.prev = null;
        }

        // 如果后置结点为空，表示当前结点是最后一个结点
        if (next == null) {
            last = pre;
        } else {
            next.prev = pre;
            x.next = null;
        }
        size--;
    }

    /**
     * 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
     */
    public Node removeLast() {
        final Node l = last;
        Node prev = l.prev;
        last = prev;
        if (prev == null) {
            // 如果最后一个结点的前置结点是空，表示last并非最后的结点，last已经置为null，让头结点也变为空，变成一个全新的链表
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        return l;
    }

    /**
     * 返回链表⻓度，时间 O(1)
     */
    public int size() {
        return size;
    }
}
