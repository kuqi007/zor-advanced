package com.zor.algorithm.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 832. 翻转图像
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1,0],[1,0,1],[0,0,0]]
 * 输出: [[1,0,0],[0,1,0],[1,1,1]]
 * 解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * 示例 2:
 * <p>
 * 输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 说明:
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 * Created by kuqi0 on 2021/2/24
 */
public class Leetcode832 {

    public static void main(String[] args) {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] ints = flipAndInvertImage(A);
        System.out.println(Arrays.deepToString(ints));


    }

    public static int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            //水平翻转图片
            int[] arr = A[i];
            int[] temp = new int[arr.length];
            Deque<Integer> stack = new LinkedList<>();
            for (int i1 : arr) {
                stack.push(i1);
            }
            int j = 0;
            while (!stack.isEmpty()) {
                //反转图片
                temp[j] = 1 - stack.poll();
                j++;
            }
            A[i] = temp;
        }
        return A;
    }

    public static int[][] solution2(int[][] A){

        for (int[] nums : A) {
            int n=nums.length;
            int low=0;
            int high=n-1;
            while (low<=high){
                int temp=nums[low];
                nums[low++]=nums[high]^1;
                nums[high--]=temp^1;
            }

        }
        return A;
    }


}
