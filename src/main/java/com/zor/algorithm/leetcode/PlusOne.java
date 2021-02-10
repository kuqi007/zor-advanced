package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 * <p>
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 * <p>
 * 输入：digits = [0]
 * 输出：[1]
 *
 * @author zqq
 * @date 2021/2/9
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] ints = plusOne(new int[]{9});
        System.out.println(Arrays.toString(ints));
    }

    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            // 关键条件，如果不为0，则表示没有进位，直接return；反之表示进位，继续遍历，进位之后下一位要+1
            if (digits[i] != 0)
                return digits;
        }

        // 首位是1，其余位默认为0
        digits = new int[digits.length + 1];
        digits[0] = 1;

        return digits;
    }

    /**
     * 笨写法
     *
     * @param digits
     * @return
     */
    public static int[] solution2(int[] digits) {

        // 进一位
        int upper = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];

            digit = digit + upper;

            digits[i] = digit % 10;
            upper = digit / 10;
        }

        if (upper != 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }

        return digits;

    }


}
