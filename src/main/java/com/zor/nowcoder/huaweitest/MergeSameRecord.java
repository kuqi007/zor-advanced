package com.zor.nowcoder.huaweitest;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by zqq on 2020/2/5.
 * 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * <p>
 * 输入描述:
 * 先输入键值对的个数
 * 然后输入成对的index和value值，以空格隔开
 * <p>
 * 输出描述:
 * 输出合并后的键值对（多行）
 * <p>
 * 示例1
 * 输入
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出
 * 0 3
 * 1 2
 * 3 4
 */
public class MergeSameRecord {
    public static void main(String[] args) {
        solution1();
    }

    private static void solution1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            Map<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < count; i++) {
                // 空格也可作为下一个输入，key value属于同一行
                int key = scanner.nextInt();
                int value = scanner.nextInt();
                if (treeMap.containsKey(key)) {
                    treeMap.put(key, treeMap.get(key) + value);
                } else {
                    treeMap.put(key, value);
                }
            }
            for (int key : treeMap.keySet()) {
                System.out.println(key + " " + treeMap.get(key));
            }
            //treeMap.forEach((key, value) -> {
            //    System.out.println(key + " " + value);
            //});
        }

    }
}
