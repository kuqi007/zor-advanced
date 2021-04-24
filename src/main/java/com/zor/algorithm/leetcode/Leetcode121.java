package com.zor.algorithm.leetcode;

/**
 * @author zqq
 * @date 2021/3/24
 */
public class Leetcode121 {
    public static void main(String[] args) {
        int[] arr = {7, 6, 4, 3, 1};
        int i = solution0(arr);
        System.out.println(i);
    }

    /**
     * 记住几个点
     * 1. 用一个变量记录最低买入价格，如果当天价格比之前的最低买入低，那么这天肯定不会卖出，我们选择今天买入，minPrice=curPrice
     * 2. 记录一个收益profit，如果当天买入价格比之前最低买入价高，则考虑是否在今天卖出，比较下今天卖出的收益是否大于上一次可卖出收益，
     *    如果是，则取今天卖出，继续遍历，直到最后一天。
     */
    public static int solution0(int[] prices) {
        int minPrice = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            // 如果
            if (price < minPrice) {
                minPrice = price;
            } else {
                profit = Math.max(price - minPrice, profit);
            }
        }

        return profit;
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
