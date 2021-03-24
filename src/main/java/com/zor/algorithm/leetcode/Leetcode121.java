package com.zor.algorithm.leetcode;

/**
 * @author zqq
 * @date 2021/3/24
 */
public class Leetcode121 {
    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        int i = maxProfit(arr);
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        // 最小买入价格
        int minPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > profit) {
                profit = price - minPrice;
            }
        }
        return profit;
    }

}
