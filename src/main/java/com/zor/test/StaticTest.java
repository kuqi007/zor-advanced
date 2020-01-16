package com.zor.test;

/**
 * Created by zqq on 2019/7/29.
 */
public class StaticTest {
    protected static void staticMethodTest() {
        System.out.println("111");
    }

    protected void methodTest() {

    }
}

class A extends StaticTest {
    /**
     * 静态方法不能显示重写，但是可以使用子类去调用
     */
    protected static void staticMethodTest() {
        System.out.println("222");
    }

    public static void main(String[] args) {
        A.staticMethodTest();
    }
}

class B {
    public static void main(String[] args) {
        // protect方法在派生类或者当前类文件中可以访问
        StaticTest.staticMethodTest();
        StaticTest aopTest = new StaticTest();

        aopTest.methodTest();
    }
}

