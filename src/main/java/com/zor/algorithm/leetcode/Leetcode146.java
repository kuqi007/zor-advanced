package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * <p>
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 * @author zqq
 * @date 2021/3/11
 */
public class Leetcode146 {
    static class LRUCache {
        private final int capacity;
        private final Map<Integer, Node> map;
        private final MyLinkedList myLinkedList;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            myLinkedList = new MyLinkedList();
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;

            myLinkedList.moveToFirst(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node existE = map.get(key);
            if (existE == null) {
                // 如果key不存在，判断容量是否超出
                if (myLinkedList.size() == capacity) {
                    Node last = myLinkedList.removeLast();
                    map.remove(last.key);
                }
                Node node = new Node(key, value);
                // 添加到头部
                myLinkedList.addFirst(node);
                map.put(key, node);
            } else {
                // 如果key已存在，修改element的value，并放到头部
                existE.value = value;
                myLinkedList.moveToFirst(existE);
            }
        }
    }

    static class MyLinkedList {
        private int size = 0;
        private final Node head;
        private final Node tail;

        public MyLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public void remove(Node x) {
            final Node prev = x.prev;
            final Node next = x.next;
            prev.next = next;
            next.prev = prev;
            x.prev = null;
            x.next = null;
            size--;
        }

        public void addFirst(Node x) {
            final Node first = head.next;
            first.prev = x;
            head.next = x;
            x.prev = head;
            x.next = first;
            size++;
        }

        public Node removeLast() {
            final Node last = tail.prev;
            remove(last);
            return last;
        }

        public void moveToFirst(Node x) {
            remove(x);
            addFirst(x);
        }

        public int size() {
            return size;
        }

    }

    static class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        System.out.println(i);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        System.out.println(i1);
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        System.out.println(i2);
        int i3 = lRUCache.get(3);// 返回 3
        System.out.println(i3);
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println(i4);
        //LRUCache lRUCache = new LRUCache(2);
        //lRUCache.put(2, 1);
        //lRUCache.put(1, 2);
        //lRUCache.put(2, 3);
        //lRUCache.put(4, 1);
        //int i2 = lRUCache.get(1);
        //int i3 = lRUCache.get(2);
    }

}
