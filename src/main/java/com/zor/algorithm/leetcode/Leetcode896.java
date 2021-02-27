package com.zor.algorithm.leetcode;

/**
 * 896. 单调数列
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：[1,1,1]
 * 输出：true
 * Created by kuqi0 on 2021/2/28
 */
public class Leetcode896 {
    public static void main(String[] args) {

    }

    public boolean isMonotonic(int[] A) {
        int n = A.length;
        int incrCnt = 0, decrCnt = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] <= A[i + 1]) {
                incrCnt++;
            }
            if (A[i] >= A[i + 1]) {
                decrCnt++;
            }
        }
        return incrCnt == n - 1 || decrCnt == n - 1;
    }

    public boolean solution1(int[] A) {
        int n = A.length;
        boolean isIncr = true, isDecr = true;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] > A[i + 1]) {
                isIncr = false;
            } else if (A[i] < A[i + 1]) {
                isDecr = false;
            }
            // 如果遇到两个false的情况，直接返回false
            if (!isIncr && !isDecr) {
                return false;
            }
        }
        return true;
    }

    public boolean solution2(int[] A) {
        int n = A.length;
        boolean isIncr = false, isDecr = false;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] > A[i + 1]) {
                isDecr = true;
            } else if (A[i] < A[i + 1]) {
                isIncr = true;
            }
            if (isIncr && isDecr) {
                return false;
            }
        }
        return true;
    }


}
