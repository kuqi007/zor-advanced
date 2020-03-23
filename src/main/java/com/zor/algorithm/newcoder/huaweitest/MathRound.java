package com.zor.algorithm.newcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/5.
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 *
 * 输入描述:
 * 输入一个正浮点数值
 *
 * 输出描述:
 * 输出该数值的近似整数值
 *
 * 示例1
 * 输入
 * 5.5
 * 输出
 * 6
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
