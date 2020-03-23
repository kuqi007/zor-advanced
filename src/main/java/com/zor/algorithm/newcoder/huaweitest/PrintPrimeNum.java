package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/3.
 * 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 *
 * 最后一个数后面也要有空格
 *
 * 详细描述：
 *
 * 函数接口说明：
 *
 * public String getResult(long ulDataInput)
 *
 * 输入参数：
 * long ulDataInput：输入的正整数
 * 返回值：
 * String
 *
 * 输入描述:
 * 输入一个long型整数
 *
 * 输出描述:
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
 *
 * 示例1
 * 输入
 * 180
 * 输出
 * 2 2 3 3 5
 */
public class PrintPrimeNum {
    public static void main(String[] args) {
        solution1();
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        System.out.println(getResult(num));

        //for (int i = 2; i <= Math.sqrt(num); i++) {
        //    if (num % i == 0) {
        //        System.out.print(i + " ");
        //        num /= i;
        //        i--;
        //    }
        //
        //}
        //System.out.println(num);

    }

    public static String getResult(long num) {
        int pum = 2;
        StringBuilder result = new StringBuilder();
        while (num != 1) {
            while (num % pum == 0) {
                num = num / pum;
                result.append(pum).append(" ");
            }
            pum++;
        }
        return result.toString();
    }
}
