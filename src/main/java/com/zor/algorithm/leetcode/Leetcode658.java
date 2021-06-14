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
        int[] arr = {1, 2, 3, 4, 5};
        List<Integer> res = leetcode658.findClosestElements(arr, 4, 3);
        System.out.println(res);
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length;
        int idx = bsearch(arr, x);
        if (idx < 0) {
            for (int i = 0; i < k; i++) {
                res.add(arr[i]);
            }
        } else if (idx >= n) {
            for (int i = n - 1 - k; i < n; i++) {
                res.add(arr[i]);
            }
        } else if (arr[idx] == x) {
            // 找到的情况
            if ((k - 1) % 2 == 0) {
                int cnt = (k - 2) / 2;
                for (int i = idx - cnt; i <= idx + cnt; i++) {
                    res.add(arr[i]);
                }
            } else {
                int cnt = (k - 1) / 2;
                for (int i = idx - cnt - 1; i <= idx + cnt; i++) {
                    res.add(arr[i]);
                }
            }
        } else {
            // 未找到的情况



        }

        return res;

    }

    private int bsearch(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
