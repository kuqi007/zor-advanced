package com.zor.algorithm.leetcode;

/**
 * No.9
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 */
public class PalindromeNumber {
    public static void main(String[] args) {

        System.out.println(solution3(12121));

    }

    public static boolean isPalindrome(int x) {

        return solution1(x);

    }

    private static boolean solution1(int x) {
        String reversedStr = (new StringBuilder(x + "")).reverse().toString();
        return (x + "").equals(reversedStr);
    }

    /**
     * 解法二：进阶解法---数学解法
     * 通过取整和取余操作获取整数中对应的数字进行比较。
     * <p>
     * 举个例子：1221 这个数字。
     * <p>
     * 通过计算 1221 / 1000， 得首位1
     * 通过计算 1221 % 10， 可得末位 1
     * 进行比较
     * 再将 22 取出来继续比较
     * <p>
     * 作者：MisterBooo
     * 链接：https://leetcode-cn.com/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
     */
    private static boolean solution2(int x) {
        if (x < 0) {
            return false;
        }

        int div = 1;

        while (x / div >= 10) div *= 10;

        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }


    public static boolean solution3(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }


}
