package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/6.
 */
public class ReverseString {
    public static void main(String[] args) {
        solution1();
        //solution2();
    }


    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        for (int i = next.length() - 1; i >= 0; i--) {
            char c = next.charAt(i);
            System.out.print(c);
        }
    }

    private static void solution2() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        while (i != 0) {
            int a = i % 10;
            System.out.print(a);
            i = i / 10;
        }
    }
}
