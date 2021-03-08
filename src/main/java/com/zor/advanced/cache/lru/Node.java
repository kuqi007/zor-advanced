package com.zor.advanced.cache.lru;

/**
 * Created by kuqi0 on 2021/3/9
 */
public class Node {

    public int key, val;
    public Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
