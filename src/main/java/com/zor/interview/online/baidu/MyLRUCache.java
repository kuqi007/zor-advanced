package com.zor.interview.online.baidu;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 百度面试题
 * Created by kuqi0 on 2021/3/8
 * 使用双向链表实现一个LRU的cache
 * <p>
 * 首先要接收⼀个capacity参数作为缓存的最⼤容量，然后实现两个API，⼀个是 put(key, val) 方法存入键值对，
 * 另一个是 get(key) 方法获取 key 对应的 val，如果 key 不存在则返回空。
 * 注意哦，get 和 put ⽅法必须都是  O(1)  的时间复杂度
 * 参考 https://www.cnblogs.com/-beyond/p/13026406.html
 */
public class MyLRUCache {

    /**
     * 缓存数据的容器
     */
    private final Map<String, DLinkedNode> cache;

    private final int capacity;

    /**
     * 当前缓存中的数据数量
     */
    private int count = 0;

    /**
     * 双向链表的头尾节点（数据域key和value都为null）
     */
    private final DLinkedNode head;
    private final DLinkedNode tail;

    /**
     * 唯一构造器，进行初始化
     *
     * @param capacity 最多能保存的缓存项数量
     */
    public MyLRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        // 头结点
        head.pre = null;
        head.next = tail;
        // 尾节点
        tail.pre = head;
        tail.next = null;
    }

    /**
     * 从缓存中获取数据
     *
     * @param key 缓存中的key
     * @return 缓存的value
     */
    private Integer get(String key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 每次访问后，就需要将访问的key对应的节点移动到第一个位置（最近访问）
        moveToFirst(node);
        return node.value;
    }

    /**
     * 向缓存中添加数据
     *
     * @param key   元素key
     * @param value 元素value
     */
    public void set(String key, Integer value) {
        // 先尝试从缓存中获取key对应缓存项（node）
        DLinkedNode existNode = cache.get(key);
        if (existNode == null) {
            DLinkedNode listNode = new DLinkedNode();
            listNode.key = key;
            listNode.value = value;

            // 放入缓存
            cache.put(key, listNode);
            // 将新加入的节点存入双链表，且放到第一个位置
            addNodeToFirst(listNode);
            count++;
            if (count > capacity) {
                DLinkedNode delNode = delLastNode();
                // 如果node只存value，那么将无法将map中的key删除
                cache.remove(delNode.key);
                count--;
            }
        } else {
            // key对应的数据已存在，则进行覆盖
            existNode.value = value;
            // 如果存在需要移动
            moveToFirst(existNode);
        }
    }


    /**
     * 添加新节点到双向链表（新加入的节点位于第一个位置）
     *
     * @param newNode 新加入的节点
     */
    private void addNodeToFirst(DLinkedNode newNode) {
        // 先将新节点的左右两边置为头结点和原来的第一个节点
        newNode.pre = head;
        newNode.next = head.next;
        // 再将头节点和原来的第一个节点与新节点连上，顺序不能乱，否则会导致环形链表
        head.next.pre = newNode;
        head.next = newNode;
    }

    /**
     * 删除双向链表的尾节点（淘汰节点）
     *
     * @return 被删除的节点
     */
    private DLinkedNode delLastNode() {
        DLinkedNode last = tail.pre;
        // 删除最后一个节点而不是删除尾节点，尾节点保持不变
        delNode(last);
        return last;
    }

    /**
     * 将节点移动到双向链表的第一个位置
     *
     * @param node 需要移动的节点
     */
    private void moveToFirst(DLinkedNode node) {
        // 删除delNode
        delNode(node);
        // 把节点添加第一位
        addNodeToFirst(node);
    }


    /**
     * 删除双链表的节点（直接连接前后节点)
     *
     * @param node 要删除的节点
     */
    private void delNode(DLinkedNode node) {
        DLinkedNode previousPre = node.pre;
        DLinkedNode previousNext = node.next;
        previousPre.next = previousNext;
        previousNext.pre = previousPre;
    }


    public static void main(String[] args) {
        MyLRUCache cache = new MyLRUCache(3);
        cache.set("one", 111);
        System.out.println(cache.get("one"));   // 111

        cache.set("two", 222);
        System.out.println(cache.get("two"));   // 222

        cache.set("three", 333);
        System.out.println(cache.get("three")); // 333

        cache.set("four", 444);
        System.out.println(cache.get("four"));  // 444

        System.out.println(cache.get("one"));   //null

    }

    /**
     * 双向链表
     */
    static class DLinkedNode {
        /**
         * 如果node只存value，那么将超出容量时将无法将map中的对应key的node删除
         */
        public String key;
        public Integer value;
        public DLinkedNode next;
        public DLinkedNode pre;
    }

}


