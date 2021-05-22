package com.zor.algorithm.interview.online.didi;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author zhuqiqi03
 * @date 2021/4/28
 */
public class EasyCalculator {

    public static void main(String[] args) {
        EasyCalculator easyCalculator = new EasyCalculator();
        String s = "11+2-5-5+58+1111-12345111";
        long res = easyCalculator.solution1(s);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object result = engine.eval(s);
            System.out.println("实际结果:" + result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        System.out.println("算法结果: " + res);

    }

    public int solution1(String s) {
        s = s + "+";
        char preSign = '+';
        int res = 0;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                if (preSign == '+') {
                    res = res + num;
                } else {
                    res = res - num;
                }
                preSign = c;
                num = 0;
            }

        }
        return res;
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
