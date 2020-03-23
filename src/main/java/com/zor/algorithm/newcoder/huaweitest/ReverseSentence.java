package com.zor.algorithm.newcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/6.
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 */
public class ReverseSentence {
    public static void main(String[] args) {
        //solution1();
        solution2();
    }


    private static void solution1() {
        Scanner scanner = new Scanner(System.in);

        String next = scanner.nextLine();
        String[] strings = next.split(" ");
        for (int i = strings.length - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.print(strings[i]);
            } else {
                System.out.print(strings[i] + " ");
            }
        }
    }

    private static void solution2() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        System.out.println(reverse(next));
    }


    public static String reverse(String sentence) {
        String[] strings = sentence.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--) {
            stringBuilder.append(strings[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
