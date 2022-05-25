package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * No 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 *  
 * <p>
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 链接：<a href="https://leetcode-cn.com/problems/remove-element">...</a>
 *
 * @author zqq
 * @date 2021/1/13
 */
public class Leetcode27 {
    public static void main(String[] args) {
        System.out.println(solution3(new int[]{1,1,1,5}, 1));
    }

    public static int solution0(int[] nums, int val) {
        int i = 0;
        for (int num : nums) {
            if (num != val) {
                nums[i] = num;
                i++;
            }
        }
        return i;
    }

    /**
     * 双指针解法有点绕
     * 拆成两部分看，i是有效数组，j是拿来比较的
     */
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            System.out.println("i：" + i + " j:" + j);
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            //System.out.println(Arrays.toString(nums));
        }
        return i;
    }

    /**
     * 这个比较通俗易懂
     */
    public static int solution2(int[] nums, int val) {
        int ans = 0;
        for (int num : nums) {
            if (num != val) {
                nums[ans] = num;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 顺序无所谓，
     */
    public static int solution3(int[] nums, int val) {
        int ans = nums.length;
        for (int i = 0; i < ans; ) {
            if (nums[i] == val) {
                nums[i] = nums[ans - 1];
                // 只要出现目标元素则-1，所以最后数组长度即ans
                ans--;
                //System.out.println(ans);
                //System.out.println(i);
                // i不加1是因为可能换过来的数也可能等于val
            } else {
                i++;
            }
            System.out.println(Arrays.toString(nums));
        }
        return ans;
    }
}
