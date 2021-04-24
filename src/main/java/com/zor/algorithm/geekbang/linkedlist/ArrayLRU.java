package com.zor.algorithm.geekbang.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kuqi0 on 2021/3/27
 * 基于数组实现的LRU缓存
 * 1. 空间复杂度为O(n)
 * 2. 时间复杂度为O(n)
 * TODO 是否还有优化的空间？
 * 使用循环数组试试
 */
public class ArrayLRU {

    private final int capacity;

    private int count;

    private final Element[] elementData;

    /**
     * 保存key和index的映射关系
     */
    private final Map<Integer, Integer> holder;

    public ArrayLRU(int capacity) {
        this.capacity = capacity;
        elementData = new Element[capacity];
        count = 0;
        holder = new HashMap<>((int) Math.ceil(capacity / 0.75) + 1);
    }

    public int get(int key) {
        if (!holder.containsKey(key)) {
            return -1;
        }

        Integer index = holder.get(key);
        // index左边元素右移，原来的index元素移到第一位
        moveToHead(index);

        return elementData[0].val;
    }

    public void put(int key, int value) {
        Element node = new Element(key, value);
        if (!holder.containsKey(key)) {
            if (count == capacity) {
                Element last = removeLast();
                holder.remove(last.key);
                count--;
            } else {
                // 将原本的数据往右移一步
                rightShift(count);
            }
            // 添加到头部
            addToHead(node);
            count++;
        } else {
            // 当前元素移到左边，原来左边的元素全部右移
            Integer index = holder.get(key);
            // 先把值设置为新值
            elementData[index].val = value;
            // 然后移到头部
            moveToHead(index);
        }
    }

    private void addToHead(Element node) {
        elementData[0] = node;
        holder.put(node.key, 0);
    }

    private void moveToHead(int index) {
        Element node = elementData[index];
        rightShift(index);
        addToHead(node);
    }

    private Element removeLast() {
        int lastIndex = elementData.length - 1;
        Element last = elementData[lastIndex];
        // 数据往右移
        rightShift(lastIndex);
        return last;
    }

    /**
     * end左边的所有数据统一右移一位
     *
     * @param end end
     */
    private void rightShift(int end) {
        for (int i = end - 1; i >= 0; i--) {
            elementData[i + 1] = elementData[i];
            holder.put(elementData[i].key, i + 1);
        }
    }

    static class Element {
        int key;
        int val;

        public Element(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    public static void main(String[] args) {
        ArrayLRU lRUCache = new ArrayLRU(2);
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
    }
}
