package com.zor.algorithm.leetcode;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * Created by kuqi0 on 2022/5/24
 */
public class Leetcode04 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // todo 这破题 太难了

        return 0;
    }


    private double solution1(int[] nums1, int[] nums2) {

        return 0;
    }

    /**
     * 暴力做法，先合并数组，再找中位数
     */
    private double solution0(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] temp = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m || j < n) {
            if (j >= n || (i < m && nums1[i] < nums2[j])) {
                temp[k++] = nums1[i];
                i++;
            } else {
                temp[k++] = nums1[i];
                j++;
            }
        }
        double ans = 0;
        if (temp.length % 2 == 0) {
            ans = temp[(m + n - 1) / 2] + temp[(m + n) / 2] / 2.0;
        } else {
            ans = temp[(m + n) / 2];
        }
        return ans;
    }

}
