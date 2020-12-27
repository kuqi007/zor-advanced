package com.zor.basic.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kuqi0 on 2020/12/27
 */
public class HashSetTest {

    public static void main(String[] args) {
        // HashSet是否一定无序
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 16; i++) {
            Long value = Long.valueOf(i);
            System.out.println(value.hashCode());
            set.add(value);
        }
        System.out.println(set.toString());
    }

}
