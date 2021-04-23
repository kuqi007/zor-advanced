package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 * Created by kuqi0 on 2021/4/5
 */
public class Leetcode88 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        solution0(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

    }

    /**
     * 逆向双指针，空间复杂度为O(1)
     */
    public static void solution0(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int tmp;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 < 0) {
                tmp = nums2[p2--];
            } else if (p2 < 0) {
                tmp = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                tmp = nums1[p1--];
            } else {
                tmp = nums2[p2--];
            }
            nums1[tail--] = tmp;
        }
    }

    /**
     * 额外空间
     */
    public static void solution1(int[] nums1, int m, int[] nums2, int n) {
        int[] sorted = new int[m + n];
        //int index = 0;
        int p1 = 0, p2 = 0;
        while (p1 < m || p2 < n) {
            int tmp;
            if (p1 == m) {
                tmp = nums2[p2];
                p2++;
            } else if (p2 == n) {
                tmp = nums1[p1];
                p1++;
            } else if (nums1[p1] < nums2[p2]) {
                tmp = nums1[p1];
                p1++;
            } else {
                tmp = nums2[p2];
                p2++;
            }
            //sorted[index++] = tmp;
            // 每次p1和p2只有一个指针会移动
            sorted[p1 + p2 - 1] = tmp;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
        //System.arraycopy(sorted, 0, nums1, 0, m + n);
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int tail = m + n - 1;
        while (i >= 0 || j >= 0) {
            int temp;
            if (i == -1) {
                temp = nums2[j--];
            } else if (j == -1) {
                temp = nums1[i--];
            } else if (nums1[i] < nums2[j]) {
                temp = nums2[j--];
            } else {
                temp = nums1[i--];
            }
            nums1[tail--] = temp;
        }
    }

}
