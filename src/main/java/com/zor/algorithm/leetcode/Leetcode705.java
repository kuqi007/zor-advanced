package com.zor.algorithm.leetcode;

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains 。
 * <p>
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 * <p>
 * Created by kuqi0 on 2021/3/13
 */
public class Leetcode705 {

    static class Node {
        int key;
        Node next;

        public Node(int key) {
            this.key = key;
        }
    }

    static class MyHashSet {

        private final Node[] table;

        private final int n = 1009;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            table = new Node[n];
        }

        public void add(int key) {
            int index = hash(key);
            if (table[index] == null) {
                table[index] = new Node(key);
                return;
            }

            Node curNode = table[index];
            while (curNode.next != null) {
                if (curNode.key == key) {
                    return;
                }
                curNode = curNode.next;
            }
            if (curNode.key == key) {
                return;
            }
            curNode.next = new Node(key);
        }

        public void remove(int key) {
            int index = hash(key);
            Node curNode = table[index];
            if (curNode == null) {
                return;
            }
            Node p = curNode, e;
            // 如果第一个就是要找的元素
            if (curNode.key == key) {
                table[index] = curNode.next;
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

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int index = hash(key);
            Node curNode = table[index];
            while (curNode != null) {
                if (curNode.key == key) {
                    return true;
                }
                curNode = curNode.next;
            }
            return false;

            //if (curNode == null) return false;
            //
            //Node e;
            //if (curNode.key == key) {
            //    return true;
            //} else if ((e = curNode.next) != null) {
            //    do {
            //        if (e.key == key) {
            //            return true;
            //        }
            //    } while ((e = e.next) != null);
            //}
            //
            //return false;
        }


        private int hash(int key) {
            return key % n;
        }

    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        boolean contains1 = myHashSet.contains(1);// 返回 True
        boolean contains2 = myHashSet.contains(3);// 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        boolean contains0 = myHashSet.contains(2); // 返回 True
        myHashSet.remove(2);   // set = [1]
        boolean contains = myHashSet.contains(2);// 返回 False ，（已移除）


    }

    // 使用Boolean数组
    // static class MyHashSet {
    //    boolean[] hash;
    //
    //    /**
    //     * Initialize your data structure here.
    //     */
    //    public MyHashSet() {
    //        hash = new boolean[1000001];
    //    }
    //
    //    public void add(int key) {
    //        hash[key] = true;
    //    }
    //
    //    public void remove(int key) {
    //        hash[key] = false;
    //    }
    //
    //    /**
    //     * Returns true if this set contains the specified element
    //     */
    //    public boolean contains(int key) {
    //        return hash[key];
    //    }
    //}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}
