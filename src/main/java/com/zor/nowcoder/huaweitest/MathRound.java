package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/5.
 */
public class MathRound {
    public static void main(String[] args) {
        solution2();

    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        double d = scanner.nextDouble();
        System.out.println(Math.round(d));
        //int a = (int) d;
        //double decimal=d-a;
    }
    private static void solution2() {
        Scanner scanner = new Scanner(System.in);
        double d = scanner.nextDouble();
        //System.out.println(Math.round(d));
        int integer = (int) d;
        double decimal = d - integer;
        if (decimal >= 0.5) {
            System.out.println(integer + 1);
        } else {
            System.out.println(integer);
        }
    }
}
