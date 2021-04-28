package com.zor.algorithm.interview.online;

/**
 * Created by kuqi0 on 2021/4/28
 */
public class EasyCalculator {

    public static void main(String[] args) {
        EasyCalculator easyCalculator = new EasyCalculator();
        String expression = "11+2-1+111";
        int calc = easyCalculator.calc(expression);
        System.out.println(calc);

    }

    /**
     * 简单加减运算的计算器
     */
    public int calc(String s) {
        int num = 0;
        int res = 0;
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (c == '+' || c == '-' || i == s.length() - 1) {
                if (preSign == '+') {
                    res += num;
                } else if (preSign == '-') {
                    res -= num;
                }
                preSign = c;
                num = 0;
            }
        }
        return res;
    }
}
