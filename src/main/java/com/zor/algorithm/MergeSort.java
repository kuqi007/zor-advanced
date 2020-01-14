package com.zor.algorithm;

import java.io.IOException;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) throws IOException {

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6, 8};
        int[] result = merge(a, b);

        System.out.println(Arrays.toString(result));

    }

    /**
     * 递归合并两个已按升序排列的数组.
     *
     * <p>
     * 提示：
     * 1. 必须使用递归写法，循环写法无效。
     * 2. 可以定义辅助函数。
     * 3. test case执行时可能出现StackOverFlowError。
     * </p>
     *
     * @param a 升序排列数组，长度可能为0-10000
     * @param b 升序排列数组，长度可能为0-10000
     * @return a和b合并后的升序排列数组
     */
    public static int[] merge(int[] a, int[] b) {

        return new int[0];
    }

    private static void sort(int[] array) {



    }
}

