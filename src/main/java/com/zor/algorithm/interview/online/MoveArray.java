package com.zor.algorithm.interview.online;

import java.util.Arrays;

/**
 * 2021/2/19日 百度面试
 */
public class MoveArray {
    public static void main(String[] args) {
        // 1. 给定数组，返回长度相同的数组。在返回的数组中，第i个位置的值生成规则为：
        // a、原数组中的第i个元素，往右移动几次，能碰到一个比自身大的元素
        // b、若没有比自己大的元素，或者为最后一个元素，则置为-1
        // case:     input:  [ 6 , 2 , 7 , 3 , 2 ]
        // return: [ 2 , 1 ,-1 ,-1 , -1]
        int[] nums = {6, 2, 7, 3, 3, 1, 3, 99};
        int[] res = solution4(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 暴力解法，时间复杂度约n^2
     */
    public static int[] violent(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }


    public static int[] solution2(int[] nums) {
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[left] < nums[right]) {
                nums[left] = right - left;
                left++;
                right = left;
            } else if (right == nums.length - 1) {
                nums[left] = -1;
                left++;
                right = left;
            } else {
                right++;
            }
        }
        return nums;
    }

    /**
     * for循环遍历写法，不是很推荐
     */
    public static int[] solution3(int[] nums) {
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] < nums[i]) {
                nums[index] = i - index;
                i = index;
                index++;
            } else if (i == nums.length - 1) {
                nums[index] = -1;
                i = index;
                index++;
            }
        }
        return nums;
    }

    /**
     * 优化解法，倒过来遍历
     */
    public static int[] solution4(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int n = nums.length;
        int pre = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                res[i] = 1;
                pre = 1;
            } else {
                int j = 1;
                while (i + pre + j < n) {
                    if (nums[i] < nums[i + pre + j]) {
                        pre = pre + j;
                        res[i] = pre;
                        break;
                    } else if (i + pre + j == n - 1) {
                        res[i] = -1;
                    }
                    j++;
                }
            }
        }
        return res;
    }

}




