package com.zor.basic.collection.map;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.cnblogs.com/ideal-20/p/11146733.html
 */
public class HashSetDemo {
    public static void main(String[] args) {

        //下列程序执行结果是否有序
        Set<Integer> hs = new HashSet<Integer>();
        for (int i = 30; i >= 0; i--) {
            hs.add(i);
        }

        //增强for遍历
        for (Integer s : hs) {
            System.out.print(s + " ");
        }
    }
}
