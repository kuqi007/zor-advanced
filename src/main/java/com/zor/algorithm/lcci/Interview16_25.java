package com.zor.algorithm.lcci;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache-lcci/
 * Created by kuqi0 on 2021/5/1
 */
public class Interview16_25 {

    static class LRUCache {
        /**
         * 使用hashmap使得查询间复杂度为O(1)，否则链表时间复杂度为O(n)
         */
        private final Map<Integer, Node> cache;

        private final int capacity;
        private int size;

        private final Node head;
        private final Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            size = 0;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            // 该节点不存在
            if (node == null) {
                // 容量满了
                if (size == capacity) {
                    Node last = removeLast();
                    cache.remove(last.key);
                    size--;
                }
                node = new Node(key, value);
                addToHead(node);
                cache.put(key, node);
                size++;
            } else {
                node.value = value;
                // 节点已存在
                moveToHead(node);
            }
        }

        private Node removeLast() {
            Node last = tail.prev;
            remove(last);
            return last;
        }

        private void moveToHead(Node node) {
            remove(node);
            addToHead(node);
        }

        private void addToHead(Node node) {
            Node next = head.next;
            head.next = node;
            next.prev = node;

            node.prev = head;
            node.next = next;

        }

        private void remove(Node node) {
            Node prev = node.prev;
            Node next = node.next;

            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * 双向链表
     */
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);

        cache.put(1, 1);
        cache.put(2, 2);
        int i = cache.get(1);// 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        int i1 = cache.get(2);// 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        int i2 = cache.get(1);// 返回 -1 (未找到)
        int i3 = cache.get(3);// 返回  3
        int i4 = cache.get(4);// 返回  4

    }


}


