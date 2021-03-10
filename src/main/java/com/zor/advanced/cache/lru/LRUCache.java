package com.zor.advanced.cache.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuqi0 on 2021/3/8
 * 首先要接收⼀个capacity参数作为缓存的最⼤容量，然后实现两个API，⼀个是 put(key, val) 方法存入键值对，
 * * 另一个是 get(key) 方法获取 key 对应的 val，如果 key 不存在则返回 -1。
 * * 注意哦，get 和 put ⽅法必须都是  O(1)  的时间复杂度，
 */
public class LRUCache {

    private final Map<Integer, Node> map;

    /**
     * 可以直接换成Deque或者LinkedList
     */
    private final DoubleList cache;

    /**
     * 最大容量
     */
    private final int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        //cache = new DoubleLinkedListII();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).val;
        // 利用put方法把数据提前
        // 其实就是两步：将该节点删除，再将该节点放到头部
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);
        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新map中对应的数据
            map.put(key, x);
        } else {
            if (cap == cache.size()) {
                // 删除链表最后一个元素
                Node last = cache.removeLast();
                map.remove(last.key);
            }
            // 直接添加到头部
            cache.addFirst(x);
            map.put(key, x);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int i1 = cache.get(1);
        cache.put(3, 3);
        int i2 = cache.get(2);
        System.out.println(i2);
        cache.put(1, 4);
    }


}
