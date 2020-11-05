package com.zor.algorithm.leetcode;

import org.junit.Test;

/**
 * No.7 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
public class ReverseInteger {
    @Test
    public void test() {
        System.out.println(reverse(123456789));
    }

    /**
     * int范围 -2147483648~2147483647
     * <p>
     * 为了便于解释，我们假设 \text{rev}rev 是正数。
     * <p>
     * 如果 temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 导致溢出，那么一定有 \text{rev} \geq \frac{INTMAX}{10}rev≥
     * 10
     * INTMAX
     * ​
     * 。
     * 如果 \text{rev} > \frac{INTMAX}{10}rev>
     * 10
     * INTMAX
     * ​
     * ，那么 temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 一定会溢出。
     * 如果 \text{rev} == \frac{INTMAX}{10}rev==
     * 10
     * INTMAX
     * ​
     * ，那么只要 \text{pop} > 7pop>7，temp = \text{rev} \cdot 10 + \text{pop}temp=rev⋅10+pop 就会溢出。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode/
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            /*
             * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
             * 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
             * 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
             *
             *   首先确定rev是int类型
             *   rev*10，最后一位肯定是0
             *   ans > MAX_VALUE/10 则乘以10之后肯定比max大 假如最大值511，大于511/10=51，比如52*10=520>511
             */
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10))
                return 0;
            /*
             * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
             * 当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
             * 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
             */
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10))
                return 0;
            rev = rev * 10 + pop;
        }

        return rev;
    }

    public static int solution2(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }

    public static void main(String[] args) {
        System.out.println(solution2(-123456));
    }
}
