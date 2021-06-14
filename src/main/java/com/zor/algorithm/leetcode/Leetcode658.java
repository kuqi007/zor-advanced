package com.zor.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 * 给定一个排序好的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * 数组里的每个元素与 x 的绝对值不超过 104
 *
 * @author zhuqiqi03
 * @date 2021/6/14
 */
public class Leetcode658 {
    public static void main(String[] args) {
        Leetcode658 leetcode658 = new Leetcode658();
        int[] arr = {1, 1, 1, 10, 10, 10};
        List<Integer> res = leetcode658.solution1(arr, 4, 9);
        System.out.println(res);
    }

    public List<Integer> solution1(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        // 找到左边界
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //System.out.println(left + ":" + right);
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= left + k; i++) {
            res.add(arr[left]);
        }
        return res;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0, high = arr.length - 1;
        while (high - low >= k) {
            // 收缩左右边界
            if (x - arr[low] > arr[high] - x) {
                low++;
            } else {
                high--;
            }
        }

        //System.out.println(low);

        List<Integer> res = new ArrayList<>();
        for (; low <= high; low++) {
            res.add(arr[low]);
        }

        return res;
    }


}
