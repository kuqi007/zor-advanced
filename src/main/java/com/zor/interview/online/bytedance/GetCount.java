package com.zor.interview.online.bytedance;


/**
 * 有序数组，[1, 2, 3, 4, 4, 4,4, 5, 6, 7]
 * 给出一个数字，求在数组中出现的次数。
 * 时间复杂度要尽量低
 */
public class GetCount {
    public static void main(String[] args) {
        GetCount getCount = new GetCount();
        int[] arr = {1, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7};
        int count = getCount.bestSolution(arr, 4);
        System.out.println("出现的次数为：" + count);
    }

    /**
     * 最佳解法，两次二分查找，时间复杂度为2logN O(logN)
     */
    private int bestSolution(int[] nums, int target) {
        int l = binarySearch(nums, target);
        // 查找第一个大于目标值的下标
        int r = binarySearch(nums, target + 1);
        if (l == nums.length || nums[l] != target) {
            return -1;
        }
        return r - l;
    }


    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 先二分，再暴力，O(log n+K)，k比较大时，趋近于O(n)
     */
    private int solution1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                int n = 0;
                int i = mid - 1, j = mid;
                while (i >= 0 && nums[i] == target) {
                    i--;
                    n++;
                }
                while (j <= nums.length - 1 && nums[j] == target) {
                    j++;
                    n++;
                }
                return n;
            }
        }
        return -1;
    }


}