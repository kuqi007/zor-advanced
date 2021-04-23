package com.zor.algorithm.interview.online;

import java.util.HashMap;
import java.util.Map;

/**
 * 小红书一面
 * 2021/4/23
 */
public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Entry<K, V>> map;
    private int size;
    private final Entry<K, V> first;
    private final Entry<K, V> last;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        size = 0;
        first = new Entry<>();
        last = new Entry<>();
        first.next = last;
        last.pre = first;
    }

    public Entry<K, V> get(K k) {
        Entry<K, V> e = map.get(k);
        if (e == null) {
            return null;
        }
        moveToFirst(e);
        return e;
    }

    public void set(K k, V v) {
        if (map.get(k) == null) {
            if (size == capacity) {
                Entry<K, V> last = removeLast();
                map.remove(last.key);
                size--;
            }
            Entry<K, V> e = new Entry<>(k, v);
            map.put(k, e);
            addToFirst(e);
            size++;
        } else {
            Entry<K, V> e = map.get(k);
            moveToFirst(e);
        }
    }

    private void addToFirst(Entry<K, V> e) {
        Entry<K, V> next = first.next;
        first.next = e;
        next.pre = e;
        e.pre = first;
        e.next = next;
    }

    private void moveToFirst(Entry<K, V> e) {
        remove(e);
        addToFirst(e);
    }

    private void remove(Entry<K, V> e) {
        Entry<K, V> pre = e.pre;
        Entry<K, V> next = e.next;
        pre.next = next;
        next.pre = pre;
    }

    private Entry<K, V> removeLast() {
        Entry<K, V> pre = last.pre;
        remove(pre);
        return pre;
    }


    static class Entry<K, V> {
        Entry<K, V> pre;
        Entry<K, V> next;
        K key;
        V value;

        public Entry() {

        }

        public Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}