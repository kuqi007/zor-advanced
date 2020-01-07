package com.zor.basic.String;

import org.junit.Test;

/**
 * Created by zqq on 2019/6/12.
 */
public class StringNumberTest {

    @Test
    public void StringTest1() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "he" + "llo";//编译期间做了优化，没有生成新对象
        String s4 = "hel" + new String("lo");
        String s5 = new String("hello");
        String s6 = s5.intern();
        String s7 = "h";
        String s8 = "ello";
        String s9 = s7 + s8;
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//true
        System.out.println(s1 == s4);//false
        System.out.println(s1 == s9);//false
        System.out.println(s4 == s5);//false
        System.out.println(s1 == s6);//true
    }

    @Test
    public void StringTest2() {
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
    //TODO ??????
    @Test
    public void StringTest3() {
        String s1 = new String("hello");
        String intern1 = s1.intern();
        String s2 = "hello";
        System.out.println(s1 == s2);//false
        String s3 = new String("hello") + new String("hello");
        String intern3 = s3.intern();
        String s4 = "hellohello";
        System.out.println(s3 == s4);//为啥是true
    }

    @Test
    public void test1() {

        System.out.println(1 << 4);

    }
}
