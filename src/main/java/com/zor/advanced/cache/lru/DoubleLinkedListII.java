package com.zor.advanced.cache.lru;

/**
 * 使用一个傀儡头和傀儡尾节点，更方便操作
 *
 * @author zqq
 * @date 2021/3/10
 */
public class DoubleLinkedListII implements DoubleList {

    private final Node head;
    private final Node tail;

    private int size = 0;

    public DoubleLinkedListII() {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        // 头结点
        head.prev = null;
        head.next = tail;
        // 尾节点
        tail.prev = head;
        tail.next = null;
    }

    /**
     * 在链表头部添加节点x，时间O(1)
     */
    public void addFirst(Node x) {
        // 先将新节点的左右两边置为头结点和原来的第一个节点
        x.prev = head;
        x.next = head.next;
        // 再将头节点和原来的第一个节点与新节点连上，顺序不能乱，否则会导致环形链表
        head.next.prev = x;
        head.next = x;
        // size++
        size++;
    }

    /**
     * 删除链表中的 x 节点（x ⼀定存在）
     * 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
     */
    public void remove(Node x) {
        Node pre = x.prev;
        Node next = x.next;
        pre.next = next;
        next.prev = pre;
        // size--
        size--;
    }

    /**
     * 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
     */
    public Node removeLast() {
        Node last = tail.prev;
        remove(last);
        // size--
        size--;
        return last;
    }

    /**
     * 返回链表⻓度，时间 O(1)
     */
    public int size() {
        return size;
    }
}
