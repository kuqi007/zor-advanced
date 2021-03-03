package com.zor.algorithm.leetcode;

/**
 * @author zqq
 * @date 2021/3/2
 */
public class Leetcode304 {
    static class NumMatrix {

        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0) return;
            // 计算由原点到i,j的和
            int row = matrix.length;
            int col = matrix[0].length;
            preSum = new int[row + 1][col + 1];
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    preSum[y + 1][x + 1] = preSum[y][x + 1] + preSum[y + 1][x] - preSum[y][x] + matrix[y][x];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];

        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        NumMatrix obj = new NumMatrix(matrix);
        int param_1 = obj.sumRegion(2, 1, 4, 3);
        System.out.println(param_1);
    }
}
