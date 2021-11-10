package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuqi0 on 2021/11/8
 */
public class Leetcode146_2 {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4

    }

    static class LRUCache {
        int capacity;
        int count;
        Map<Integer, MyNode> map;
        MyNode head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            count = 0;
            map = new HashMap<>();
            head = new MyNode();
            tail = new MyNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                // 放到头部
                MyNode myNode = map.get(key);
                moveToHead(myNode);
                return myNode.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                MyNode myNode = map.get(key);
                myNode.value = value;
                moveToHead(myNode);
            } else {
                MyNode myNode = new MyNode(key, value);
                map.put(key, myNode);
                addToHead(myNode);
                count++;
                if (count > capacity) {
                    // 如果超出容量，删除队尾的元素
                    MyNode last = removeTail();
                    map.remove(last.key);
                    count--;
                }
            }
        }

        private MyNode removeTail() {
            MyNode lastNode = tail.prev;
            removeNode(lastNode);
            return lastNode;
        }


        private void removeNode(MyNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(MyNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void moveToHead(MyNode node) {
            removeNode(node);
            addToHead(node);
        }
    }


    static class MyNode {
        int key;
        int value;
        MyNode prev;
        MyNode next;

        MyNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public MyNode() {
        }
    }
}
