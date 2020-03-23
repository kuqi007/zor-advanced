package com.zor.nowcoder.huaweitest;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by zqq on 2020/2/2.
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），
 * 对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，
 * 按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作。
 * <p>
 * Input Param 
 * <p>
 *      n               输入随机数的个数     
 * <p>
 *  inputArray      n个随机整数组成的数组 
 * <p>
 * Return Value
 * <p>
 *      OutputArray    输出处理后的随机整数
 *  
 * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
 * <p>
 * 输入描述:
 * 输入多行，先输入随机整数的个数，再输入相应个数的整数
 * <p>
 * 输出描述:
 * 返回多行，处理后的结果
 * <p>
 * 输入例子:
 * 11
 * 10
 * 20
 * 40
 * 32
 * 67
 * 40
 * 20
 * 89
 * 300
 * 400
 * 15
 * <p>
 * 输出例子:
 * 10
 * 15
 * 20
 * 32
 * 40
 * 67
 * 89
 * 300
 * 400
 */
public class MingMingRandomNum {
    public static void main(String[] args) {
        solution3();

    }

    /**
     * 使用Arrays.sort方法
     */
    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            Arrays.sort(arr);

            for (int i = 0; i < n; i++) {
                if (i == 0 || arr[i] != arr[i - 1]) {
                    System.out.println(arr[i]);
                }

            }
        }
    }

    /**
     * 使用treeSet
     */
    private static void solution2() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();

            Set<Integer> set = new TreeSet<>();
            for (int i = 0; i < n; i++) {
                set.add(scanner.nextInt());
            }

            for (Integer integer : set) {
                System.out.println(integer);
            }
        }
    }

    private static void solution3() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            //初始化10001的布尔数组，最大下标为1000
            boolean[] booleans = new boolean[1001];
            for (int i = 0; i < n; i++) {
                booleans[scanner.nextInt()] = true;
            }

            for (int i = 0; i < booleans.length; i++) {
                if (booleans[i]) {
                    System.out.println(i);
                }
            }
        }
    }
}
