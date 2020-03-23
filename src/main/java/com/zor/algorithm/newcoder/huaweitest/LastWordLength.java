package com.zor.algorithm.newcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/1.
 * 题目描述
 * 计算字符串最后一个单词的长度，单词以空格隔开。
 * 输入描述:
 * 一行字符串，非空，长度小于5000。
 * <p>
 * 输出描述:
 * 整数N，最后一个单词的长度。
 * <p>
 * 示例1
 * 输入
 * hello world
 * 输出
 * 5
 */
public class LastWordLength {

    public static void main(String[] args) {
        solution1();
        //solution2();

    }


    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split("\\s+");
        System.out.println(s[s.length - 1].length());
    }

    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int count = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        System.out.println(count);

    }
}
