package com.zor.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：[3]
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 * <p>
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * Created by kuqi0 on 2021/8/13
 */
public class Leetcode229 {

    public static void main(String[] args) {
        Leetcode229 leetcode229 = new Leetcode229();
        int[] nums = {1, 2, 3, 1, 2, 3, 1};
        List<Integer> res = leetcode229.test1(nums);
        System.out.println(res);
    }

    /**
     * 摩尔投票
     */
    public List<Integer> test1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Integer cand1 = null, cand2 = null;
        int vote1 = 0, vote2 = 0;

        //// 正确顺序:
        // if (匹配候选人) → 增加计数
        // else if (count为零) → 设置新候选人
        // else → 三方抵消

        // 第一次遍历，筛选候选人
        for (int num : nums) {
            // 如果cnt1为0，重新选举候选人
            // 匹配候选人放在前面，如果count为0设置新候选人放在前面，像[2,2]这种会被认定为2个候选人，而不是1个
            if (vote1 > 0 && num == cand1) {// 如果跟cand1是同一阵营，1号计数器加1
                vote1++;
            } else if (vote2 > 0 && num == cand2) {
                vote2++;
            } else if (vote1 == 0) {
                cand1 = num;
                vote1++;
            } else if (vote2 == 0) { // 如果cnt2为0，重新选举候选人
                cand2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        // 验证是否大于1/3
        int c1 = 0, c2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == cand1) {
                c1++;
            } else if (vote2 > 0 && num == cand2) {
                c2++;
            }
        }

        if (c1 > nums.length / 3) {
            res.add(cand1);
        }
        if (c2 > nums.length / 3) {
            res.add(cand2);
        }

        return res;
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // 最多有2个众数(出现超过 ⌊n/3⌋ 次), 后面打擂台更新
        int cand1 = nums[0], cand2 = nums[0];
        int count1 = 0, count2 = 0;
        // 第1阶段 - 成对抵销
        for (int num : nums) {
            // votes1 之前已经出现过, 这里再次出现
            if (count1 > 0 && num == cand1) {
                count1++;
            } else if (count2 > 0 && num == cand2) {
                count2++;
            } else if (count1 == 0) {
                // cand1 第一次出现
                cand1 = num;
                count1++;
            } else if (count2 == 0) {
                cand2 = num;
                count2++;
            } else {
                // cand1或cand2 之前已经出现过, 但此时第3个数(竞争者)第1次出现
                count1--;
                count2--;
            }
        }

        // 第2阶段 - 计数, 数目要超过三分之一
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == cand1) count1++;
            else if (num == cand2) count2++;
        }

        if (count1 > n / 3) res.add(cand1);
        if (count2 > n / 3) res.add(cand2);

        return res;
    }


    /**
     * 摩尔投票法
     */
    public List<Integer> solution2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // 初始化两个候选人candidate，和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;

        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第一个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第二个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }
            // 如果不符合以上任意一种情况，则直接减一
            count1--;
            count2--;
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) count1++;
            else if (cand2 == num) count2++;
        }

        if (count1 > nums.length / 3) result.add(cand1);
        if (count2 > nums.length / 3) result.add(cand2);

        return result;
    }

    /**
     * 暴力解法
     */
    public List<Integer> solution1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int majorCnt = nums.length / 3;
        Map<Integer, Integer> counters = countNums(nums);
        for (Map.Entry<Integer, Integer> entry : counters.entrySet()) {
            if (res.size() > 2) {
                return res;
            }
            if (entry.getValue() > majorCnt) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        return counter;
    }


}
