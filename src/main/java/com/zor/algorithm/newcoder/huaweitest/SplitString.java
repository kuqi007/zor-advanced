package com.zor.algorithm.newcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/2.
 * 题目描述
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 输入描述:
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 * <p>
 * 输出描述:
 * 输出到长度为8的新字符串数组
 * <p>
 * 示例1
 * 输入
 * abc
 * 123456789
 * 输出
 * abc00000
 * 12345678
 * 90000000
 */
public class SplitString {
    public static void main(String[] args) {
        solution1();
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        fun2(str1);
        fun2(str2);

    }

    private static void fun(String str) {
        while (str.length() >= 8) {
            System.out.println(str.substring(0, 8));
            str = str.substring(8);
        }
        if (str.length() > 0) {
            str = str + "00000000";
            System.out.println(str.substring(0, 8));
        }

    }

    private static void fun2(String s) {

        if (s.length() % 8 != 0)
            s = s + "00000000";

        while (s.length() >= 8) {
            System.out.println(s.substring(0, 8));
            s = s.substring(8);
        }
    }
}
