package com.zor.algorithm.leetcode.binarysearch;

/**
 * Created by kuqi0 on 2021/5/6
 */
public class Leetcode367 {
    public static void main(String[] args) {
        Leetcode367 leetcode367 = new Leetcode367();
        boolean perfectSquare = leetcode367.isPerfectSquare(1);
        System.out.println(perfectSquare);

    }

    public boolean isPerfectSquare(int num) {
        // 防止溢出
        // r=num 可以覆盖数字0和1的情况
        long l = 0, r = num, x;
        while (l <= r) {
            // 无符号右移
            x = (l + r) >>> 1;
            long guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            } else if (guessSquared > num) {
                r = x - 1;
            } else {
                l = x + 1;
            }
        }
        return false;
    }
}
