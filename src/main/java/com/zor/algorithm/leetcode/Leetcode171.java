package com.zor.algorithm.leetcode;

/**
 * 171. Excel表列序号
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 * 致谢：
 * 特别感谢 @ts 添加此问题并创建所有测试用例。
 * Created by kuqi0 on 2021/6/29
 */
public class Leetcode171 {
    public static void main(String[] args) {
        Leetcode171 leetcode171 = new Leetcode171();
        System.out.println("leetcode171.titleToNumber(\"\") = " + leetcode171.titleToNumber("AB"));
    }

    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }

    public int solution1(String columnTitle) {
        int res = 0;
        int temp = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            char c = columnTitle.charAt(i);
            int num = c - 'A' + 1;
            res += (num * temp);
            temp *= 26;
        }
        return res;
    }


}
