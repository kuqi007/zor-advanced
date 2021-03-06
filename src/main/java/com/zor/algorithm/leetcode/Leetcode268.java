package com.zor.algorithm.leetcode;

import java.util.Arrays;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * <p>
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 *
 * @author zhuqiqi03
 * @date 2021/4/25
 */
public class Leetcode268 {

    public static void main(String[] args) {

        int[] nums = {1};
        int i = missingNumber(nums);
        System.out.println(i);
    }

    /**
     * 异或位运算
     */
    public static int solution4(int[] nums) {
        int n = nums.length;
        int res = n;
        for (int i = 0; i < n; i++) {
            res = res ^ i ^ nums[i];
        }
        return res;
    }

    public static int solution3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + i - nums[i];
        }
        return sum + n;
    }

    public static int solution2(int[] nums) {
        int n = nums.length;
        int sum1 = n * (n - 1) / 2;
        int sum2 = 0;
        for (int num : nums) {
            sum2 += num;
        }
        return sum1 - sum2;
    }

    public static int solution0(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if (nums[n - 1] != n) return n;
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) return i;
        }
        return -1;
    }


    /**
     * 二分法
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        //if (nums[0] != 0) return 0;
        //if (nums[n - 1] != n) return n;
        int l = 0, r = n - 1;
        //int ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            // 如果中间值和下标相等，表示缺失数字在右边
            if (mid == nums[mid]) {
                // ans保存下最接近缺失数的值（小于缺失数字）
                //ans = mid + 1;
                l = mid + 1;
            } else if (mid < nums[mid]) {
                // ans保存最后一个最接近缺失数字的值（大于缺失数字）
                //ans = mid;
                r = mid - 1;
            }
        }
        // 找到右边界r，r+1即缺失的数字 而循环结束时l=r+1，所以也可以返回l
        return r + 1;
    }

    /**
     * 二分法
     */
    public int solution1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        if (nums[n - 1] != n) return n;
        int l = 0, r = n - 1;
        while (l <= r) {
            if (r - l <= 5) {
                for (int i = l; i <= r; i++) {
                    if (nums[i] != i) return i;
                }
            } else {
                int mid = l + (r - l) / 2;
                if (mid == nums[mid]) {
                    l = mid + 1;
                } else if (mid < nums[mid]) {
                    r = mid;
                }
            }
        }
        return -1;
    }
}
