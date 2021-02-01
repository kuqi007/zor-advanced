package com.zor.basic.collection.map;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.cnblogs.com/ideal-20/p/11146733.html
 */
public class HashSetDemo {
    public static void main(String[] args) {

        //下列程序执行结果是否有序
        Set<String> hs = new HashSet<>();
        for (long i = 30L; i >= 0; i--) {
            hs.add(Long.toString(i));
        }

        //增强for遍历
        for (String s : hs) {
            System.out.print(s + " ");
        }
    }

    @Test
    public void test() {
        int size = 0;
        int n = 16;
        for (int i = 30; i >= 0; i--) {
            System.out.println((n - 1) & i);
            // 扩容
            if (++size > n * 0.75) {
                n = n << 1;
                System.out.println("扩容后大小" + n);
            }
        }
    }
}
