package com.zor.basic.highconcurrency.threadlocal;

/**
 * 两个方法参数的传递方式
 * Created by kuqi0 on 2021/3/19
 */
public class ThreadLocalTest {

    void a() {
        ContextUtil.setValue(1);
        b();
    }

    void b() {
        Integer value = ContextUtil.getValue();
        System.out.println(value);
    }

    static class ContextUtil {
        private static final ThreadLocal<Integer> value = new ThreadLocal<>();

        public static Integer getValue() {
            System.out.println("test");
            return value.get();
        }

        public static void setValue(Integer v) {
            value.set(v);
        }
    }

    public static void main(String[] args) {
        ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.a();
        ContextUtil.setValue(1111);
        new Thread(threadLocalTest::b).start();
    }
}
