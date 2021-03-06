package com.zor.algorithm.interview.online.baidu;

/**
 * 2020/2/19 百度面试 策略模式
 */
public class StrategyPatternTest {

    public void test(String casees) {
        Father father = null;
        if ("son1".equals(casees)) {
            father = new Son1();
        } else if ("son2".equals(casees)) {
            father = new Son2();
        }

        father.test();
    }


}

interface Father {
    void test();
}

class Son1 implements Father {
    public void test() {
        //
    }

}

class Son2 implements Father {
    public void test() {
        //
    }

}