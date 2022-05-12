package com.zor.algorithm.interview.online;

import java.io.IOException;
import java.util.Arrays;

/**
 * 乐言科技2020/01在线笔试题
 * Created by kuqi0 on 2021/5/15
 */
public class MergeTwoSortedArray {

    public static void main(String[] args) throws IOException {

        int[] a = {1, 2, 3, 5, 7, 9, 10};
        int[] b = {1, 2, 4, 6, 8, 10};

        System.out.println(Arrays.toString(merge(a, b)));
        System.out.println(Arrays.toString(iterate(a, b)));

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
        return recursive(a, b);
    }


    public static int[] recursive(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] tmp = new int[m + n];
        int i = 0, j = 0, k = 0;
        return helper(a, i, b, j, tmp, k);
    }

    /**
     * todo 这个递归太骚了...
     */
    private static int[] helper(int[] a, int i, int[] b, int j, int[] c, int k) {
        if (i + j == c.length) {
            return c;
        } else if (i == a.length) {
            c[k] = b[j++];
        } else if (j == b.length) {
            c[k] = a[i++];
        } else if (a[i] < b[j]) {
            c[k] = a[i++];
        } else {
            c[k] = b[j++];
        }
        k++;
        return helper(a, i, b, j, c, k);
    }

    public static int[] iterate(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        int[] ans = new int[m + n];
        int i = 0, j = 0, cur = 0;
        while (i < m || j < n) {
            if (i >= m || (j < n && a[i] > b[j])) {
                ans[cur] = b[j++];
            } else {
                ans[cur] = a[i++];
            }
            cur++;
        }
        return ans;
    }


}
