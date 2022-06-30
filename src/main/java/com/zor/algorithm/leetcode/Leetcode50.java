package com.zor.algorithm.leetcode;

import com.zor.algorithm.interview.online.MyPow;

/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * Created by kuqi0 on 2022/6/28
 */
public class Leetcode50 {
    public static void main(String[] args) {
        Leetcode50 leetcode50 = new Leetcode50();
        double res = leetcode50.solution1(2.10000, 3);
        System.out.println(res);
    }


    public double myPow(double x, long n) {
        return n > 0 ? quickMul(x, n) : 1.0 / quickMul(x, -n);
    }

    public double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        // 如果n为偶数，那么直接把上一次结果进行平方
        // 如果n是技术，平方之后额外乘上一个x
        return n % 2 == 0 ? y * y : y * y * x;
    }

    public double solution1(double x, long n) {
        return n > 0 ? pow(x, n) : 1.0 / pow(x, -n);
    }

    /**
     * <a href="https://leetcode.cn/problems/powx-n/solution/powx-n-by-leetcode-solution/">...</a>
     */
    public double pow(double x, long n) {
        // 把n当做二进制，例如x的6次方=x^4*x^2, x的10次方=x^8*x^2，
        // 那么只要判断n二进制的每一位，如果是1，那么计入贡献。
        // 从最低位开始计算
        long k = n;
        // 初始贡献值为x
        double contribute = x;
        // 初始值为1.0
        double ret = 1.0;
        while (k != 0) {
            // 最低位如果是1，那么将结果乘上x
            if ((k & 1) == 1) {
                ret *= contribute;
            }
            // 将x进行平方，因为往高位走，需要的贡献值都要平方，比如6次方（二进制110），等于x^0 * x^2 * x^4
            contribute *= contribute;
            // 舍弃最低位，这样我们每次判断最低位即可
            k >>= 1;
        }
        return ret;
    }
}
