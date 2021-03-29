package com.zor.algorithm.leetcode.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * @author zqq
 * @date 2021/3/29
 */
public class Leetcode739 {
    public static void main(String[] args) {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res = solution1(T);
        System.out.println(Arrays.toString(res));
    }

    public static int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] ans = new int[n];
        // 维护一个单调递减栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int num = T[i];
            // 如果栈不为空并且当前元素大于栈顶元素，弹出栈中比当前小的元素，并更新它们在返回数组中对应位置的值（需要等待的天数）
            // 栈里面需要保存元素在数组中的下标，而不是具体的数字。因为需要根据下标修改结果数组
            while (!stack.isEmpty() && num > T[stack.peek()]) {
                Integer preIndex = stack.pop();
                ans[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * TODO 没看太懂
     * 根据题意，从最后一天推到第一天，这样会简单很多。因为最后一天显然不会再有升高的可能，结果直接为0。
     * 再看倒数第二天的温度，如果比倒数第一天低，那么答案显然为1，如果比倒数第一天高，又因为倒数第一天
     * 对应的结果为0，即表示之后不会再升高，所以倒数第二天的结果也应该为0。
     * 自此我们容易观察出规律，要求出第i天对应的结果，只需要知道第i+1天对应的结果就可以：
     * - 若T[i] < T[i+1]，那么res[i]=1；
     * - 若T[i] > T[i+1]
     * - res[i+1]=0，那么res[i]=0;
     * - res[i+1]!=0，那就比较T[i]和T[i+1+res[i+1]]（即将第i天的温度与比第i+1天大的那天的温度进行比较）
     **/
    public static int[] solution0(int[] T) {
        int[] res = new int[T.length];
        res[T.length - 1] = 0;
        for (int i = T.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < T.length; j += res[j]) {
                if (T[i] < T[j]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 从后往前
     */
    public static int[] solution1(int[] T) {
        int[] res = new int[T.length];
        int n = T.length;
        // 保存上一个元素需要移动的次数
        int pre = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (T[i] < T[i + 1]) {
                res[i] = 1;
                pre = 1;
            } else {
                // 当前数大于后面的数，则继续往后查找
                // j = 1 是因为以前一个数为基准
                int j = 1;
                // i + pre + j 表示比前一个数大的数的下标
                while (i + pre + j < n) {
                    if (T[i] < T[i + pre + j]) {
                        pre = pre + j;
                        res[i] = pre;
                        break;
                    }
                    j++;
                }
            }
        }
        return res;
    }

    public static int[] solution2(int[] T) {
        int left = 0, right = 1;
        while (right < T.length) {
            if (T[left] < T[right]) {
                T[left] = right - left;
                left++;
                right = left;
            } else if (right == T.length - 1) {
                T[left] = 0;
                left++;
                right = left;
            } else {
                right++;
            }
        }
        return T;
    }
}
