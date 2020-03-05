package com.zor.basic.collection;

import java.util.HashMap;

public class HashMapSimpleResize {
    private static HashMap map = new HashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 13; i++) {
            map.put(new UserKey(), "1");
        }
    }
}

class UserKey {
    @Override
    //目的是然后所有的entry都在同一个哈希槽内
    public int hashCode() {
        return 1;
    }

    @Override
    //如果是true，则会对同一个key上的值进行覆盖，不会形成链表
    public boolean equals(Object obj) {
        return false;
    }
}
