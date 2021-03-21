package com.zor.algorithm.interview.online.didi;

/**
 * 大整数加法
 * 将两个字符串类型的数字相加
 * 例如"123456789" + "122986598416"
 * 可以按位parseInt
 *
 * @author zqq
 * @date 2021/2/28
 */
public class AddBigStringNum {

    public static void main(String[] args) {

        String num1 = "956";
        String num2 = "57";
        long l = solution1(num1, num2);
        System.out.println(l);

        System.out.println(Long.parseLong(num1) + Long.parseLong(num2));


    }

    public static String addBigStringNum(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        String res = "";
        // 进位
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int a = 0, b = 0;
            if (i >= 0) {
                a = num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                b = num2.charAt(j) - '0';
                j--;
            }
            int sum = a + b + carry;
            res = sum % 10 + res;
            carry = sum / 10;
        }
        return res;
    }


    public static long addStringNum(String s1, String s2) {
        int n;
        if (s1.length() - s2.length() > 0) {
            n = s1.length();
        } else {
            n = s2.length();
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }
        int offset = s1.length() - s2.length();

        long res = 0;
        int mod = 0;
        long radix = 1;
        for (int i = n - 1; i >= 0; i--) {
            int a, b;
            a = s1.charAt(i) - '0';

            b = i - offset >= 0 ? s2.charAt(i - offset) - '0' : 0;

            int sum = a + b + mod;
            res = res + (long) (sum % 10) * radix;
            radix *= 10;
            mod = sum / 10;
        }

        return res;

    }

    public static long solution1(String s1, String s2) {
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int mod = 0;
        long res = 0, radix = 1;
        while (i >= 0 || j >= 0) {
            int a = 0, b = 0;
            if (i >= 0) {
                a = s1.charAt(i--) - '0';
            }
            if (j >= 0) {
                b = s2.charAt(j--) - '0';
            }
            int sum = a + b + mod;
            res = res + (long) (sum % 10) * radix;
            radix *= 10;
            mod = sum / 10;
        }
        return mod == 1 ? radix * mod + res : res;
    }


}
