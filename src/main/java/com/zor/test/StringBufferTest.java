package com.zor.test;

/**
 * @author zqq
 * @date 2020/11/5
 */
public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a, b);
        // 结果为AB,B
        // 形参的引用指向新的引用不会影响实参
        System.out.println(a + "," + b);


    }

    public static void operator(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x;
    }
}
