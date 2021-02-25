package com.zor.algorithm.interview.online;

import java.math.BigInteger;

/**
 * @author zqq
 * @date 2021/2/25
 */
public class AddStringNumber {
    public static void main(String[] args) {
        // 两个字符串数字如何相加
        String a = "1";
        String b = "2";
        int i = (a.charAt(0) - '0') + (b.charAt(0) - '0');
        System.out.println(i);

    }

}