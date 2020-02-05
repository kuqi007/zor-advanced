package com.zor.nowcoder.huaweitest;

import java.util.Scanner;

/**
 * Created by zqq on 2020/2/5.
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
 * 输入描述:
 * 输入N个字符，字符在ACSII码范围内。
 * 输出描述:
 * 输出范围在(0~127)字符的个数。
 * 示例1
 * 输入
 * abc
 * 输出
 * 3
 */
public class CountUniqueString {
    public static void main(String[] args) {
        solution1();
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            int count = 0;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                // 仅当该字符第一次出现
                if (c <= 127 && input.indexOf(c) == i) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
