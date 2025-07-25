package com.zor.algorithm.leetcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
 * <p>
 * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * <p>
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
 * 对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
 * 对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 示例 2:
 * <p>
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *
 * @author zqq
 * @date 2021/3/29
 */
public class Leetcode496 {

    public static void main(String[] args) {
        Leetcode496 leetcode496 = new Leetcode496();
        int[] n1 = {4, 1, 2};
        int[] n2 = {1, 3, 4, 2};

        int[] res = leetcode496.violet(n1, n2);
        System.out.println(Arrays.toString(res));

    }

    public int[] violet(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            int num = nums1[i];
            int j = 0;
            // 先找到j
            while (j < m && num != nums2[j]) j++;
            // 再找比j大的元素，要先跳过自己
            while (j < m && num >= nums2[j]) j++;
            ans[i] = j < m ? nums2[j] : -1;
        }
        return ans;
    }

    public int[] test1(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Arrays.fill(ans, -1);
        // 找更大元素，构造递减栈
        Deque<Integer> stack = new LinkedList<>();
        // 因为nums1是nums2的子集，如果我提前在nums2找到每个元素的下一个更大的元素，并存储起来，那么我在遍历nums1的时候，就不用再遍历nums2
        // 因为nums没有重复元素，用一个map存储nums2中每个元素的下一个更大元素
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums2) {
            while (!stack.isEmpty() && n > stack.peek()) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }
        for (int i = 0; i < nums1.length; i++) {
            int n = nums1[i];
            if (map.containsKey(n)) {
                ans[i] = map.get(n);
            }
        }

        return ans;
    }


    /**
     * 单调栈解法
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            while (!stack.isEmpty() && nums2[stack.peek()] < num) {
                map.put(nums2[stack.pop()], num);
            }
            stack.push(i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i];
            Integer next = map.get(num);
            if (next != null) {
                nums1[i] = next;
            } else {
                nums1[i] = -1;
            }
        }


        return nums1;
    }
}
