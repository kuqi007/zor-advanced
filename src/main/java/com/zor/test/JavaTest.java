package com.zor.test;

import org.junit.Test;

public class JavaTest {


    public static void main(String[] args) {

        //((JavaTest) null).print();

        Thread t = new Thread() {


            @Override
            public void run() {
                print();
            }
        };

        t.start();//start会延时一会才执行run

        // java多线程时如果执行start（）方法并不是线程立即执行，
        // 而是进入就绪状态，必须还要保证当前线程获取到资源以后调用run()才能开始执行；
        // 因为此时或许还有其他线程正在使用需要的资源，那么当前线程会进入阻塞状态，直到所需的资源被释放。

        t.run();//run方法则是立即执行

        System.out.println("AAA");

    }

    public static void print() {

        System.out.println("hello");
    }

    @Test
    public void iAddTest() {
        int result = 0;
        int i = 1;
        //i++先赋值再自增（进case 1），++i则是先自增再赋值（进case 2）
        switch (++i) {
            case 1:
                result = result + i;
                break;
            case 2:
                result = result + i * 2;
                break;
            case 3:

                result = result + i * 3;
                break;


        }

        System.out.println(result);


    }

    public void stringTest() {


    }


}
