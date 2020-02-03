package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/2.
 * 题目描述
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 * <p>
 * 输入描述:
 * 输入一个十六进制的数值字符串。
 * <p>
 * 输出描述:
 * 输出该数值的十进制字符串。
 * <p>
 * 示例1
 * 输入
 * 0xA
 * 输出
 * 10
 */
public class Hex2Dec {
    public static void main(String[] args) {
        //solution1();
        solution2();
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            //System.out.println(Integer.parseInt(s.substring(2), 16));
            System.out.println(Integer.decode(s));
        }
    }

    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //去掉0x，将小写换成小写
            String str = sc.next().substring(2).toUpperCase();
            char[] ch = str.toCharArray();
            int sum = 0;
            for (int i = 0; i < ch.length; i++) {
                //幂值，与数组下标相反
                double exponent = ch.length - 1 - i;
                //参见ascii码表
                //https://www.path8.net/tn/archives/48
                if (ch[i] >= 'A' && ch[i] <= 'F') {
                    sum += ((int) ch[i] - ('A' - 1) + 9) * Math.pow(16, exponent);
                } else if (ch[i] >= '0' && ch[i] <= '9') {
                    sum += ((int) ch[i] - '0') * Math.pow(16, exponent);
                } else {
                    throw new RuntimeException("输入错误");
                }
            }
            System.out.println(sum);
        }
    }
}

