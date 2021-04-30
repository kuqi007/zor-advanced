package com.zor.algorithm.interview.online.didi;

import java.util.Arrays;

/**
 * 滴滴一面
 * 寻找0-n缺失的数
 * Created by kuqi0 on 2021/4/27
 */
public class FoundMissingNumberTest {

    public static void main(String[] args) {
        FoundMissingNumberTest onlineTest = new FoundMissingNumberTest();
        int[] arr = {1, 2, 4, 5, 6, 7, 8, 9, 10};

        System.out.println("onlineTest.test(arr) = " + Arrays.toString(onlineTest.test1(arr)));
    }

    /**
     * 如果缺两个数字，分情况讨论
     */
    public int[] test1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] ans = new int[2];
        boolean foundFirst = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] - i == 1 && !foundFirst) {
                ans[0] = i;
                foundFirst = true;
            } else if (nums[i] - i == 2) {
                // 如果在找到第一个数字的情况下，那此时找到的就是第二个数字，否则代表这两个数字全部缺失
                if (!foundFirst) {
                    ans[0] = i;
                }
                ans[1] = i + 1;
                break;
            }
        }
        return ans;
    }

    /**
     * 有序集合，缺一个
     */
    public int test(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        //int ans = -1;
        //if (arr[n - 1] != n) return n;
        //if (arr[0] != 0) return 0;
        while (l <= r) {
            //if (r - l < 5) {
            //    for (int i = l; i <= r; i++) {
            //        if (arr[i] != i) {
            //            return i;
            //        }
            //    }
            //}
            int mid = l + (r - l) / 2;
            // 如果mid等于当前值，说明缺失的在右边
            if (mid == arr[mid]) {
                //ans = mid + 1;
                l = mid + 1;
            } else {
                // mid小于当前值，缺失的在左边
                r = mid - 1;
            }
        }
        return l;
    }

}
