package com.zor.algorithm.leetcode;

/**
 * @author zqq
 * @date 2021/3/24
 */
public class Leetcode1528 {

    public static void main(String[] args) {

        String s = "codeleet";
        int[] indices = {4, 5, 6, 7, 0, 2, 1, 3};
        System.out.println("restoreString(s,indices) = " + restoreString(s, indices));

    }

    public static String restoreString(String s, int[] indices) {
        char[] res = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            res[indices[i]] = s.charAt(i);
        }
        return String.valueOf(res);
    }
}
