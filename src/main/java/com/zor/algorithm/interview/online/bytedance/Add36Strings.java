package com.zor.algorithm.interview.online.bytedance;

/**
 * 36进制加法：
 * <p>
 * 36进制由0-9，a-z，共36个字符表示。
 * <p>
 * 要求按照加法规则计算出任意两个36进制正整数的和，如1b + 2x = 48  （解释：47+105=152）
 * <p>
 * 要求：不允许使用先将36进制数字整体转为10进制，相加后再转回为36进制的做法
 *
 * @author zqq
 * @date 2021/3/21
 */
public class Add36Strings {
    public static void main(String[] args) {
        Add36Strings add36Strings = new Add36Strings();
        System.out.println("add36Strings(\"1\", \"a\") = " + add36Strings.add36Strings("1", "a"));
        System.out.println("add36Strings(\"1b\", \"2x\") = " + add36Strings.add36Strings("1b", "2x"));
        System.out.println("add36Strings(\"1z\", \"1\") = " + add36Strings.add36Strings("1z", "1"));
    }

    public String add36Strings(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int i = n1 - 1, j = n2 - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = 0;
            if (i >= 0) {
                x = getInt(num1.charAt(i));
                i--;
            }
            int y = 0;
            if (j >= 0) {
                y = getInt(num2.charAt(j));
                j--;
            }
            int sum = x + y + carry;
            res.append(getChar(sum % 36));
            carry = sum / 36;
        }
        return res.reverse().toString();
    }

    /**
     * 将字符转换为数字
     */
    private int getInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else {
            return c - 'a' + 10;
        }
    }

    /**
     * 将数字转换为字符
     */
    private char getChar(int n) {
        if (n <= 9) {
            return (char) (n + '0');
        } else {
            return (char) (n - 10 + 'a');
        }
    }
}
