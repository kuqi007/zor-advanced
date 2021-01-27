package com.zor.algorithm.interview.online;

import com.google.common.collect.Lists;
import com.sun.imageio.plugins.common.I18N;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 滴滴面试
 * 找出一个数组中出现次数为奇数的元素，并输出这些元素
 *
 * @author zqq
 * @date 2021/1/26
 */
public class GetOddNumbersTest {
    public static void main(String[] args) {
        // 
        int[] arr = {0, 0, 3, 3, 3, 1, 1, 2, 2, 777, 777, 888};
        List<Integer> print = print(arr);
        System.out.println(print);

        List<Integer> mapPrint = mapPrint(arr);
        System.out.println(mapPrint);

    }

    private static List<Integer> print(int[] a) {

        List<Integer> result = Lists.newArrayList();

        if (a.length == 0) {
            return result;
        }

        // 快排
        Arrays.sort(a);

        int last = a[0];
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            int num = a[i];
            if (num == last) {
                count++;
            } else {
                if (count % 2 != 0) {
                    result.add(last);
                }
                count = 1;
            }
            last = num;
        }

        if (count % 2 != 0) {
            result.add(last);
        }

        return result;
    }

    private static List<Integer> mapPrint(int[] a) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : a) {
            if (map.containsKey(i)) {
                Integer count = map.get(i);
                map.put(i, count + 1);
            } else {
                map.put(i, 1);
            }
        }

        List<Integer> result = new ArrayList<>();
        map.forEach((i, count) -> {
            if (count % 2 != 0) {
                result.add(i);
            }
        });


        return result;
    }
}
