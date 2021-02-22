package com.zor.algorithm.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by kuqi0 on 2021/2/23
 */
public class TopKTest {
    public static void main(String[] args) {

        String[] arr = new String[]{"1", "1", "1", "2", "2", "3", "3", "4", "4"};
        List<String> topN = getTopN(arr, 3);
        System.out.println(topN);

    }

    public static List<String> getTopN(String[] arr, int k) {

        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else if (queue.peek() != null && entry.getValue() > queue.peek().getValue()) {
                queue.poll();
                queue.add(entry);
            }
        }

        List<String> ret = new ArrayList<>(k);
        while (k-- > 0 && queue.peek() != null) {
            ret.add(queue.poll().getKey());
        }

        return ret;

    }
}
