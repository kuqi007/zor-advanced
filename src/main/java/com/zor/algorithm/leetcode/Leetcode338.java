package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * <p>
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * <p>
 * 要求算法的空间复杂度为O(n)。
 * <p>
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。<p>
 *
 * @author zqq
 * @date 2021/3/3
 */
public class Leetcode338 {

    public static void main(String[] args) {
        int[] ints = countBits(5);
        System.out.println(Arrays.toString(ints));

    }

    public static int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            ans[i] = countOnes(i);
        }
        return ans;
    }

    private static int countOnes(int x) {

        int count = 0;
        while (x > 0) {
            x &= (x - 1);
            count++;
        }
        return count;
    }

}
