package com.zor.algorithm.newcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/6.
 * 十进制转换为二进制
 *
 */
public class Dec2Bin {
    public static void main(String[] args) {
        solution1();
        //solution2();
    }


    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int count = 0;
        while (i != 0) {
            int b = i % 2;
            if (b == 1) {
                count++;
            }
            i = i / 2;
        }
        System.out.println(count);
    }

    private static void solution2() {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int count = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                count++;
            }
            n = n >> 1;
        }
        System.out.println(count);
    }
}
