package com.zor.algorithm.interview.online;

import java.util.Scanner;
import java.util.*;

public class MoveArray {
    public static void main(String[] args) {
        // 1. 给定数组，返回长度相同的数组。在返回的数组中，第i个位置的值生成规则为：
        // a、原数组中的第i个元素，往右移动几次，能碰到一个比自身大的元素
        // b、若没有比自己大的元素，或者为最后一个元素，则置为-1
        // case:     input:  [ 6 , 2 , 7 , 3 , 2 ]
        // return: [ 2 , 1 ,-1 ,-1 , -1]
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("test");
        int[] nums = {6, 2, 7, 3, 2};
        int[] res = test(nums);
        System.out.println(Arrays.toString(res));
    }

    public static int[] move(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[index]) {
                res[index] = i - index;
                index++;
                i = index;
            }
        }
        return res;
    }


    public static int[] test(int[] nums) {
        int i = 0, j = 1;
        while (j <= nums.length - 1) {
            if (nums[j] > nums[i]) {
                nums[i] = j - i;
                i++;
                j = i;
            } else if (j == nums.length - 1) {
                nums[i] = -1;
                j = i;
            }
            j++;
        }
        return nums;
    }
}




