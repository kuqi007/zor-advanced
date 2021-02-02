package com.zor.algorithm.leetcode;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 * <p>
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 * <p>
 * 输入：s = " "
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 *
 * @author zqq
 * @date 2021/2/2
 */
public class LengthOfLastWord {
    public static void main(String[] args) {

        int i = solution2("Today is a nice day ");
        System.out.println(i);
    }

    public static int lengthOfLastWord(String s) {

        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {

            char c = s.charAt(i);
            if (c == ' ') {
                // 从后往前遍历，如果是空格开头则继续遍历
                if (length == 0) {
                    continue;
                }
                // 如果继续出现空格则退出遍历
                break;
            }
            length++;
        }
        return length;
    }

    public static int solution2(String s) {
        // 从后往前走，定位到第一个不为空格的字符位置
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        // 继续往前走，定位到第一个空格的位置，两者差值则为单词的长度
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }

}
