package com.zor.nowcoder.huaweitest;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zqq on 2020/2/5.
 * 题目描述
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * <p>
 * 输入描述:
 * 输入一个int型整数
 * <p>
 * 输出描述:
 * 按照从右向左的阅读顺序，返回一个不含重复数字的新的整数
 * <p>
 * 示例1
 * 输入
 * 9876673
 * 输出
 * 37689
 */
public class ExtractUniqueNum {
    public static void main(String[] args) {
        //solution1();
        //solution2();
        solution3();
    }

    private static void solution3() {
        Scanner sc = new Scanner(System.in);
        int n;
        String str;
        int count = 0;
        char ch;
        char star = '*';

        while (sc.hasNext()) {
            n = sc.nextInt();
            str = Integer.toString(n);

            while (count < str.length()) {
                ch = str.charAt(str.length() - 1 - count);
                if (ch != star)
                    System.out.print(ch);
                str = str.replace(ch, star);
                count++;
            }

        }
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int input = scanner.nextInt();
            StringBuilder s = new StringBuilder();
            while (input != 0) {
                int i = input % 10;
                if (s.indexOf(String.valueOf(i)) == -1) {
                    s.append(i);
                }
                input = input / 10;
            }
            System.out.println(s);
        }
    }

    /**
     * 使用LinkedHashSet
     */
    private static void solution2() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            Set<Character> integerSet = new LinkedHashSet<>(str.length());
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = str.length() - 1; i >= 0; i--) {
                integerSet.add(str.charAt(i));
            }
            for (Character character : integerSet) {
                stringBuilder.append(character);
            }
            System.out.println(stringBuilder);
        }
    }
}
