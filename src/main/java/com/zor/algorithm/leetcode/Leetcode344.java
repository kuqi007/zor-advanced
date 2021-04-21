package com.zor.algorithm.leetcode;


/**
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * Created by kuqi0 on 2021/4/20
 */
public class Leetcode344 {

    public static void main(String[] args) {

        solution1("hello".toCharArray());

    }

    public static void reverseString(char[] s) {

        int n = s.length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < i) {
            char temp = s[j];
            s[j] = s[i];
            s[i] = temp;
            j++;
            i--;
        }
        System.out.println(new String(s));

    }


    public static void solution1(char[] s) {
        // 折半交换
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char temp = s[i];
            s[i] = s[n - i - 1];
            s[n - i - 1] = temp;
        }
        System.out.println(new String(s));

    }


}
