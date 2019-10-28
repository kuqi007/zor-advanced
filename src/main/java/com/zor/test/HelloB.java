package com.zor.test;

public class HelloB extends HelloA {

    public HelloB() {

        System.out.println(111);
    }


    {
        System.out.println(222);
    }

    static {

        System.out.println(333);
    }

    /**
     * 答案
     * 666
     * 333
     * start
     * 555
     * 444
     * 222
     * 111
     * end
     *
     * @param args
     */
    public static void main(String[] args) {


        System.out.println("start");

        new HelloB();

        System.out.println("end");
    }

}


class HelloA {

    public HelloA() {
        System.out.println(444);

    }

    {
        System.out.println(555);
    }


    static {
        System.out.println(666);
    }
}