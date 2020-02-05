package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/3.
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
