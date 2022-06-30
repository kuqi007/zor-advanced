package com.zor.algorithm.interview.online;

import com.zor.algorithm.leetcode.Leetcode50;

/**
 * 花旗一面面试题，求x的n次方，要求时间复杂度logN
 * Created by kuqi0 on 2022/6/28
 */
public class MyPow {

    public static void main(String[] args) {
        Leetcode50 leetcode50 = new Leetcode50();
        double res = leetcode50.myPow(2, -2);
        System.out.println(res);
    }
}
