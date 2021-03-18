package com.zor.algorithm.leetcode.slidingwindow;

/**
 * 1052. 爱生气的书店老板
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * @author zqq
 * @date 2021/2/23
 */
public class Leetcode1052 {
    public static void main(String[] args) {
        int i = solution1(new int[]{3}, new int[]{1}, 1);
        System.out.println(i);
    }

    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int left = 0, right = 0;
        int sums = 0;
        int res = 0;

        while (right < n) {
            // 把生气的加上
            sums += customers[right] * grumpy[right];
            while (right - left >= X) {
                // 把生气的减掉
                sums -= customers[left] * grumpy[left];
                left++;
            }
            res = Math.max(res, sums);
            right++;
        }

        // 将不生气的加上
        for (int i = 0; i < grumpy.length; i++) {
            // 不生气的为0，生气为1，把1踢掉，0包含进去
            res += customers[i] * (1 - grumpy[i]);
        }

        return res;
    }

    public static int solution1(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += customers[i] * (1 - grumpy[i]);
        }

        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = increase;
        for (int i = X; i < n; i++) {
            // 如果是1的代表生气，0的代表不生气直接乘就可以（仍为0），因为之前已经算出来了
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }

    public static int solution2(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
                customers[i] = 0;
            }
        }

        int max = 0, cur = 0;
        for (int l = 0, r = 0; r < n; r++) {
            cur += customers[r];
            if (r - l >= X) {
                cur -= customers[l];
                l++;
            }
            max = Math.max(max, cur);
        }
        return ans + max;
    }
}
