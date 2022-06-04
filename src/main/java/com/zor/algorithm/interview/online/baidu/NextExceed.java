package com.zor.algorithm.interview.online.baidu;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 2021/2/19日 百度面试
 */
public class NextExceed {
    public static void main(String[] args) {
        // 1. 给定数组，返回长度相同的数组。在返回的数组中，第i个位置的值生成规则为：
        // a、原数组中的第i个元素，往右移动几次，能碰到一个比自身大的元素
        // b、若没有比自己大的元素，或者为最后一个元素，则置为-1
        // case:     input:  [ 6 , 2 , 7 , 3 , 2 ]
        // return: [ 2 , 1 ,-1 ,-1 , -1]
        //int[] nums = {6, 2, 7, 3, 3, 1, 3, 99};
        int[] nums = {5, 3, 1, 2, 4};
        //int[] nums = {6, 2, 7, 3, 2};
        int[] ints = nextExceed(nums);
        System.out.println("单调栈调用结果:" + Arrays.toString(ints));
        int[] res = solution4(nums);
        System.out.println("正确结果：" + Arrays.toString(res));
    }

    /**
     * <a href="https://zhuanlan.zhihu.com/p/26465701">...</a>
     * 这题跟leetcode739是一样的https://leetcode-cn.com/problems/daily-temperatures/
     * 单调栈解法，此为最优解，时间空间均为 O(N)
     * 单调递减栈
     */
    public static int[] nextExceed(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // 填充-1
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 如果栈不为空并且当前元素大于栈顶元素，弹出栈中比当前小的元素，并更新它们在返回数组中对应位置的值（移动的步数）
            // 栈里面需要保存元素在数组中的下标，而不是具体的数字。因为需要根据下标修改结果数组
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            // 下标入栈
            stack.push(i);
        }
        return res;
    }


    /**
     * 暴力解法，时间空间复杂度约n^2
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


    /**
     * 时间复杂度n^2
     */
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
     * for循环遍历写法，不是很推荐，时间复杂度n^2
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
     * 优化解法，倒过来遍历，时间复杂度约为 n*logN
     */
    public static int[] solution4(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        int n = nums.length;
        // 保存上一个元素需要移动的次数
        int pre = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                res[i] = 1;
                pre = 1;
            } else {
                // 当前数大于后面的数，则继续往后查找
                // j = 1 是因为以前一个数为基准
                int j = 1;
                // i + pre + j 表示比前一个数大的数的下标
                while (i + pre + j < n) {
                    if (nums[i] < nums[i + pre + j]) {
                        pre = pre + j;
                        res[i] = pre;
                        break;
                    }
                    j++;
                }
            }
        }
        return res;
    }

}




