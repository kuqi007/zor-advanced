package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuqi0 on 2021/3/14
 */
public class Leetcode706 {

    static class Node {
        int key;
        int value;
        Node next;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    static class MyHashMap {

        private final Node[] table;

        private final int n = 10009;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            table = new Node[n];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            Node cur = table[key % n];
            if (cur == null) {
                table[key % n] = new Node(key, value);
            } else {
                Node p;
                do {
                    p = cur;
                    if (cur.key == key) {
                        if (cur.value != value) {
                            // 需要替换
                            cur.value = value;
                        }
                        return;
                    }
                } while ((cur = cur.next) != null);
                p.next = new Node(key, value);
            }
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            Node cur = table[key % n];
            while (cur != null) {
                if (cur.key == key) {
                    return cur.value;
                }
                cur = cur.next;
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            Node cur = table[key % n];
            if (cur == null) return;
            Node p = cur, e;
            if (cur.key == key) {
                table[key % n] = cur.next;
            } else if ((e = p.next) != null) {
                do {
                    if (e.key == key) {
                        // 切断该元素
                        p.next = e.next;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        int i = myHashMap.get(1);// 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        int i1 = myHashMap.get(3);// 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        int i2 = myHashMap.get(2);// 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        int i3 = myHashMap.get(2);// 返回 -1（未找到），myHashMap 现在为 [[1,1]]

    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
