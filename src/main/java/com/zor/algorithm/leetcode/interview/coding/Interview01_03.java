package com.zor.algorithm.leetcode.interview.coding;

/**
 * @author zqq
 * @date 2021/3/22
 */
public class Interview01_03 {
    public static void main(String[] args) {
        System.out.println("replaceSpaces(\"Mr John Smith    \", 13) = " + solution1("Mr John Smith       ", 13));
    }

    public static String solution1(String S, int length) {
        char[] chs = S.toCharArray();
        int i = length - 1, j = S.length() - 1;
        while (i >= 0) {
            if (chs[i] == ' ') {
                chs[j--] = '0';
                chs[j--] = '2';
                chs[j--] = '%';
            } else {
                chs[j--] = chs[i];
            }
            i--;
        }
        return String.valueOf(chs, j + 1, S.length() - j - 1);
    }

    public static String solution2(String S, int length) {
        char[] chars = new char[S.length()];
        int i = 0, j = 0;
        while (i < length) {
            char c = S.charAt(i);
            if (c == ' ') {
                chars[j] = '%';
                chars[j + 1] = '2';
                chars[j + 2] = '0';
                j = j + 2;
            } else {
                chars[j] = c;
            }
            i++;
            j++;
        }
        return new String(chars).trim();
    }
}
