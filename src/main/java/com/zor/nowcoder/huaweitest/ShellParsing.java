package com.zor.nowcoder.huaweitest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * linux shell脚本中${xxx}代表变量xxx的值，给定一个字符串中存在一个或多个${xxx},需要将字符串中所有${xxx}替换成实际值
 * 输入描述：
 * 第一行为赋值语句的行数
 * 第二行开始后面的每一个行为一个变量的赋值语句，左边是变量名，右边是变量的值，变量的值是纯字符串，且整个一行都没有空格，${xxx}代表变量xxx的值
 * 注：
 * 1）同一个变量不存在多次赋值
 * 2）变量的赋值语句可以再=在变量引用的后面，比如
 *  xxx=lyf/${ttt}/test
 *  ttt=www
 *  lyf/${ttt}/test中${ttt}还是要用www替换，xxx的值为lyf/www/test
 * 3)字符串长度不超过100个字符
 * 4)变量之间不存在循环引用的情况，比如下面这种场景是不存在的：
 *  xxx=lyf/${ttt}/test
 *  ttt=ccc/${xxx}
 * 输出描述：
 * 1. 最后一行左边变量的额值
 * 示例
 * 输入
 * 4
 * xxx=lyf/${ttt}/test
 * ttt=www
 * yyy=seeyou
 * aa=/aaa/${xxx}/bbb/${yyy}/ccc
 * 输出
 * /aaa/lyf/www/test/bbb/seeyou/ccc
 * 说明
 * 一共四行赋值语句，最后一行左边变量是aa，对应值为/aaa/${xxx}/bbb/${yyy}/ccc，${xxx}代表变量xxx的值，即lyf/${ttt}/test，
 * ${ttt}代表变量ttt的值，即www，所以${xxx}即为lyf/www/test，${yyy}代表变量yyy的值，即seeyou，
 * 所以最后aa的值为 /aaa/lyf/www/test/bbb/seeyou/ccc，所以输出为最后一行左边变量的值：/aaa/lyf/www/test/bbb/seeyou/ccc
 */
public class ShellParsing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            Map<String, String> map = new HashMap<>();
            String last = "";
            for (int i = 0; i < count; i++) {
                String next = scanner.next();
                if (i == count - 1) {
                    last = next;
                } else {
                    map.put(next.split("=")[0], next.split("=")[1]);
                }
            }

            String regex = "\\$\\{[^}]+?}";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(last);
            while (match.find()) {
                String group = match.group();
                String s = group.replaceAll("[${}]", "");
                String value = getValue(s, map);
                last = last.replace(group, value);
            }
            System.out.println(last.split("=")[1]);
        }
    }

    private static String getValue(String key, Map<String, String> map) {
        if (map.containsKey(key)) {
            String originValue = map.get(key);
            String regex = "\\$\\{[^}]+?}";
            Pattern pattern = Pattern.compile(regex);
            Matcher match = pattern.matcher(originValue);
            while ((match.find())) {
                String group = match.group();
                String s = group.replaceAll("[${}]", "");
                String value = getValue(s, map);
                originValue = originValue.replace(group, value);
            }
            return originValue;
        } else {
            return "";
        }
    }
}
