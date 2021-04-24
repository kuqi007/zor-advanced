package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * Created by kuqi0 on 2021/4/24
 */
public class Leetcode70 {
    public static void main(String[] args) {
        System.out.println(solution2(45));
    }


    public static int solution2(int n) {
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    public static int solution3(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1, second = 2;
        for (int i = 3; i <= n; ++i) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**
     * 滚动数组
     */
    public static int solution1(int n) {
        if (n == 1) {
            return 1;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    static Map<Integer, Integer> cache = new HashMap<>();

    /**
     * 记忆化递归
     */
    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            int i = climbStairs(n - 1) + climbStairs(n - 2);
            cache.put(n, i);
            return i;
        }
    }

}
