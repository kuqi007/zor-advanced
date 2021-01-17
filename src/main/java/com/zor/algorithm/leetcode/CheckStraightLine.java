package com.zor.algorithm.leetcode;

/**
 * No 1232. 缀点成线
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 * <p>
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= coordinates.length <= 1000
 * coordinates[i].length == 2
 * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
 * coordinates 中不含重复的点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * Created by kuqi0 on 2021/1/17
 */
public class CheckStraightLine {

    public static boolean checkStraightLine(int[][] coordinates) {

        for (int i = 1; i < coordinates.length - 1; i++) {
            int y1 = coordinates[i][1] - coordinates[i - 1][1];
            int x1 = coordinates[i][0] - coordinates[i - 1][0];
            int y2 = coordinates[i + 1][1] - coordinates[i][1];
            int x2 = coordinates[i + 1][0] - coordinates[i][0];
            if (y2 * x1 != y1 * x2) {
                return false;
            }
        }
        return true;
    }

}
