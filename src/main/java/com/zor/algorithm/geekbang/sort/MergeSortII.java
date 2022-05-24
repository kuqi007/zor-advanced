package com.zor.algorithm.geekbang.sort;

import java.util.Arrays;

/**
 * Created by kuqi0 on 2022/5/24
 */
public class MergeSortII {
    public static void main(String[] args) {
        MergeSortII mergeSort = new MergeSortII();
        int[] nums = {1, 8, 3, 6, 5, 4, 7, 2, 9, 1};
        mergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }

    private void mergeSort(int[] nums, int p, int r) {
        if (p >= r) return;
        int q = p + (r - p) / 2;
        mergeSort(nums, p, q);
        mergeSort(nums, q + 1, r);
        merge(nums, p, q, r);
    }


    private void merge(int[] nums, int p, int q, int r) {
        int[] tmp = new int[r - p + 1];

        int i = p, j = q + 1;
        int k = 0;
        while (i <= q || j <= r) {
            if (j > r || (i <= q && nums[i] < nums[j])) {
                tmp[k++] = nums[i++];
            } else {
                tmp[k++] = nums[j++];
            }
        }

        for (i = 0; i <= r - p; i++) {
            nums[p + i] = tmp[i];
        }
    }
}
