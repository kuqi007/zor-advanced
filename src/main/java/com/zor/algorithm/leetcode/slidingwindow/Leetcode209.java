package com.zor.algorithm.leetcode.slidingwindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/minimum-size-subarray-sum">https://leetcode.cn/problems/minimum-size-subarray-sum</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * Created by kuqi0 on 2022/5/10
 */
public class Leetcode209 {

    public static void main(String[] args) {
        Leetcode209 leetcode209 = new Leetcode209();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int i = leetcode209.minSubArrayLen(7, nums);
        //System.out.println(i);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int l = 0, r = 0;
        int ans = Integer.MAX_VALUE, sum = 0;
        while (r < n) {
            sum += nums[r];
            // TODO 滑动窗口 想象右边界拖着左边界走的感觉
            // 滑动窗口正常情况下找一个最大区间，那么第二层循环条件一般是不满足的条件，不满足情况下缩小窗口，让区间满足条件
            // 本题的话，是要找一个最小区间，第二层循环要写满足的条件，在满足的情况下缩写情况？？？有一点懵
            while (sum >= target) {
                ans = Math.min(ans, r - l + 1);
                System.out.println(ans);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return ans;
    }
}
