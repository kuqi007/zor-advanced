package com.zor.basic.String;

import org.junit.Test;

/**
 * Created by zqq on 2019/6/12.
 */
public class StringNumberTest {

    @Test
    public void test() {
        Integer i1 = 100, i2 = 100, i3 = 200, i4 = 200;

        System.out.println(i1 == i2);//true IntegerCache -128~127
        System.out.println(i3 == i4);//false

        String a = "s";
        String b = "s";
        System.out.println(a == b);//true

        String c = "Helloworld";
        String d = "Hello" + "world";
        System.out.println(c == d);//true

        String a1 = "a";
        String a2 = "b";
        String a3 = "ab";
        String a4 = a1 + a2;
        System.out.println(a3 == a4);//false

        String e = new String("hello");
        String f = new String("hello");
        System.out.println(e == f);//false

        String s1 = "ab";
        final String s2 = "b";
        final String s3 = "a";
        String s4 = s3 + s2;
        System.out.println(s1 == s4);//true

    }

    @Test
    public void test1() {

        System.out.println(1 << 4);

    }
}
