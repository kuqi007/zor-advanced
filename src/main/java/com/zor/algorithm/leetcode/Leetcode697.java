package com.zor.algorithm.leetcode;

import lombok.var;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 * <p>
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数
 *
 * @author zqq
 * @date 2021/2/20
 */
public class Leetcode697 {

    public static void main(String[] args) {

    }

    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        Map<Integer, int[]> mapDegree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (mapDegree.containsKey(nums[i])) {
                mapDegree.get(nums[i])[0]++;
                mapDegree.get(nums[i])[2] = i;
            } else {
                mapDegree.put(nums[i], new int[]{1, i, i});
            }
        }

        int maxNum = 0, minLength = 0;
        for (Map.Entry<Integer, int[]> entry : mapDegree.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLength = arr[2] - arr[1] + 1;
            } else if (arr[0] == maxNum) {
                if (arr[2] - arr[1] + 1 < minLength) {
                    minLength = arr[2] - arr[1] + 1;
                }
            }
        }

        return minLength;
    }

    public int solution2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }

        var maxOccur = counts.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();
//            counts.entrySet().removeIf(it -> !it.getValue().equals(maxOccur));

        int left = 0, right = 0;
        Map<Integer, Integer> slideCounts = new HashMap<>();
        int minLen = Integer.MAX_VALUE;

        while (right < nums.length) {
            var rn = nums[right];
            slideCounts.merge(rn, 1, Integer::sum);

            var cnt = slideCounts.get(rn);

            if (cnt >= maxOccur) {
                while (nums[left] != rn) {
                    slideCounts.merge(nums[left], -1, Integer::sum);
                    left++;
                }
                if (minLen > right - left + 1) {
                    minLen = right - left + 1;
                }
            }

            right++;
        }

        return minLen;
    }


}
