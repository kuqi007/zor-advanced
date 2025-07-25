package com.zor.algorithm.leetcode.slidingwindow;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
 *
 * @author zqq
 * @date 2021/2/4
 */
public class Leetcode643 {

    public static void main(String[] args) {
        //double v = solution2(new int[]{-6662, 5432, -8558, -8935, 8731, -3083, 4115, 9931, -4006, -3284, -3024, 1714, -2825, -2374, -2750, -959, 6516, 9356, 8040, -2169, -9490, -3068, 6299, 7823, -9767, 5751, -7897, 6680, -1293, -3486, -6785, 6337, -9158, -4183, 6240, -2846, -2588, -5458, -9576, -1501, -908, -5477, 7596, -8863, -4088, 7922, 8231, -4928, 7636, -3994, -243, -1327, 8425, -3468, -4218, -364, 4257, 5690, 1035, 6217, 8880, 4127, -6299, -1831, 2854, -4498, -6983, -677, 2216, -1938, 3348, 4099, 3591, 9076, 942, 4571, -4200, 7271, -6920, -1886, 662, 7844, 3658, -6562, -2106, -296, -3280, 8909, -8352, -9413, 3513, 1352, -8825}
        //        , 90);

        int[] nums = {4, 2, 1, 3, 3};
        double v = test1(nums, 2);
        System.out.println(v);
    }

    /**
     * 这个写法简单明了，注意，当窗口为k个时候才更新max值，不足k个的窗口不该参与比较
     */
    public static double test2(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        while (r < n) {
            sum += nums[r];
            // 当区间不满足条件，即窗口大于k个，收缩窗口，l右移
            if (r - l + 1 > k) {
                sum -= nums[l];
                l++;
            }

            // 当窗口为k个时候才更新max值，不足k个的窗口不该参与比较
            if (r - l + 1 == k) {
                max = Math.max(max, sum);
            }
            r++;
        }

        return 1.0 * max / k;
    }

    public static double test1(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for (r = k; r < n; r++, l++) {
            sum = sum + nums[r] - nums[l];
            max = Math.max(sum, max);
        }

        return 1.0 * max / k;
    }


    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int max = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(sum, max);
        }

        return 1.0 * max / k;
    }


    public static double solution1(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int max = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            max = Math.max(max, sum);
        }
        return 1.0 * max / k;
    }

    public static double solution2(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        while (right < n) {
            sum += nums[right++];
            if (right - left == k) {
                // max写在内部，因为必须要加三个数的和，比如10，-1，-2，如果写在if外面，那么最后的结果为10，就不对了
                ans = Math.max(ans, sum);
                sum -= nums[left++];
            }
        }
        return 1.0 * ans / k;
    }


}
