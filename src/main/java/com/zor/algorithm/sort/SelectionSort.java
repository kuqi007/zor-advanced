package com.zor.algorithm.sort;

import java.util.Arrays;

/**
 * Created by zqq on 2020/3/18.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arrays = new int[]{9, 3, 10, 1, 8};
        sort(arrays);
    }

    private static void sort(int[] nums) {
        int pos;
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            pos = 0;
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j] > nums[pos]) {
                    pos = j;
                }
            }
            temp = nums[pos];
            nums[pos] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
            System.out.println(Arrays.toString(nums));
        }
    }
}
