package com.zor.algorithm.leetcode;

import io.netty.handler.ssl.OpenSsl;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @author zqq
 * @date 2021/3/4
 */
public class Leetcode16 {
    public static void main(String[] args) {
        int i = solution2(new int[]{1, 1, 1, 1}, 4);
        System.out.println(i);
    }

    /**
     * 不是很优雅
     */
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int minOffset = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = cur + nums[l] + nums[r];
                if (sum == target) {
                    return target;
                }
                int offset = target - sum;
                if (sum < target) {
                    // 大于0
                    if (minOffset > 0) {
                        minOffset = Math.min(minOffset, offset);
                    } else if (minOffset < 0 && -offset > minOffset) {
                        minOffset = offset;
                    }
                    l++;
                } else {
                    // offset小于0
                    if (minOffset < 0) {
                        minOffset = Math.max(minOffset, offset);
                    } else if (minOffset > 0 && -offset < minOffset) {
                        minOffset = offset;
                    }
                    r--;
                }
            }

        }
        return target - minOffset;
    }

    public static int solution2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int cur = nums[i];
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = cur + nums[l] + nums[r];
                if (sum == target) return sum;
                // 如果sum和target差值绝对值小于ans和target差值，ans=sum
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    // ans替换掉
                    ans = sum;
                } else if (sum < target) {
                    int l0 = l + 1;
                    while (l0 < r && nums[l] == nums[l0]) {
                        l0++;
                    }
                    l = l0;
                } else {
                    // 如果和大于 target，移动 c 对应的指针
                    int r0 = r - 1;
                    // 移动到下一个不相等的元素
                    while (l < r0 && nums[r] == nums[r0]) {
                        // 此循环代表多走几步
                        r0--;
                    }
                    r = r0;
                }
            }
        }
        return ans;
    }

}
