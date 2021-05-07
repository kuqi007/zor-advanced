package com.zor.algorithm.geekbang.binarysearch;

/**
 * 二分查找
 * Created by kuqi0 on 2021/5/5
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = {3, 5, 6, 8, 9, 10};
        int i = binarySearch.findLastLesserNumber(nums, 7);
        System.out.println(i);
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    public int findLastLesserNumber(int[] a, int value) {
        int n = a.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] > value) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 查找第一个大于等于给定值的元素
     */
    public int findFirstBiggerNumber(int[] a, int value) {
        int n = a.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 通俗易懂的查找左边界写法
     */
    public int bSearchLeftBound(int[] a, int value) {
        int low = 0, high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) return mid;
                else high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 通俗易懂的查找右边界写法
     */
    public int bSearchRightBound(int[] a, int value) {
        int n = a.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == n - 1 || a[mid] != a[mid + 1]) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 直接返回
                return mid;
            }
        }
        // 直接返回
        return -1;
    }

    /**
     * 搜索左边界
     */
    public int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 收缩右侧边界，进而锁定左边界
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            }
        }
        // 检查出界情况（以及未找到）
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    public int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 收缩左边边界，锁定右侧边界
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 最后要检查 right 越界的情况（或者未找到）
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

    public int recursiveBinarySearch(int[] nums, int target) {
        return bSearchInternally(nums, 0, nums.length - 1, target);
    }

    private int bSearchInternally(int[] nums, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (nums[mid] == value) {
            return mid;
        } else if (nums[mid] < value) {
            return bSearchInternally(nums, mid + 1, high, value);
        } else {
            return bSearchInternally(nums, low, mid - 1, value);
        }
    }

}
