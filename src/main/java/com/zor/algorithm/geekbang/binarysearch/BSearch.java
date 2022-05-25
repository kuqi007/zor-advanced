package com.zor.algorithm.geekbang.binarysearch;

/**
 * Created by kuqi0 on 2022/5/24
 */
public class BSearch {

    public static void main(String[] args) {
        BSearch binarysearch = new BSearch();
        int[] nums = {3, 5, 6, 8, 9, 10};
        int ans = binarysearch.bSearchRecursive(nums, 11);
        System.out.println(ans);
    }

    private int bsearch(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    private int bSearchRecursive(int[] nums, int target) {
        return recursive(nums, target, 0, nums.length - 1);
    }

    private int recursive(int[] nums, int target, int l, int r) {
        if (l > r) return -1;
        int mid = l + ((r - l) >> 1);
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return recursive(nums, target, mid + 1, r);
        } else {
            return recursive(nums, target, l, mid - 1);
        }
    }


    private int leftBound(int[] nums, int target) {


        return target;
    }

}
