package com.zor.advanced.cache.lru;

/**
 * 双链表工具类
 * Created by kuqi0 on 2021/3/9
 */
interface DoubleList {
    /**
     * 在链表头部添加节点x，时间O(1)
     */
    void addFirst(Node x);

    /**
     * 删除链表中的 x 节点（x ⼀定存在）
     * 由于是双链表且给的是⽬标 Node 节点，时间 O(1)
     */
    void remove(Node x);

    /**
     * 删除链表中最后⼀个节点，并返回该节点，时间 O(1)
     */
    Node removeLast();

    /**
     * 返回链表⻓度，时间 O(1)
     */
    int size();

}
