package com.zor.interview.online.didi;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 滴滴面试
 * 大文件里面有很多单次，找出重复最多的前10个单词
 *
 * @author zqq
 * @date 2021/2/22
 */
public class TopKTest {
    public static void main(String[] args) {

        String[] s = new String[]{"1", "1", "1", "2", "2"
                , "2", "2", "3", "3", "3", "3", "3", "4", "4", "4", "4", "4", "4"};

        List<String> test = solution3(s, 3);
        System.out.println(test);
    }

    /**
     * 大文件里面有很多单次，找出重复最多的前10个单词
     * 使用实体类
     */
    public static List<String> getTopK(String[] s, int k) {
        List<String> ret = new ArrayList<>();
        // 先遍历
        Map<String, Long> map = getCountMap(s);

        // 构建最小堆
        Queue<WordEntity> queue = new PriorityQueue<>(Comparator.comparingLong(WordEntity::getCount));
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            Long count = entry.getValue();
            String word = entry.getKey();
            // 没到k个时候
            if (queue.size() < k) {
                queue.add(new WordEntity(word, count));
            } else if (queue.peek() != null && count > queue.peek().getCount()) {
                // 到K个时候，判断是否count大于堆顶元素，如果是，删掉堆顶元素，加入新元素重新构建堆
                queue.poll();
                queue.add(new WordEntity(word, count));
            }
        }

        while (k-- > 0 && queue.peek() != null) {
            ret.add(queue.poll().getWord());
        }
        return ret;
    }

    /**
     * 不使用实体类，使用Map的entry
     */
    public static List<String> solution2(String[] arr, int k) {

        Map<String, Long> map = getCountMap(arr);

        Queue<Map.Entry<String, Long>> queue = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));
        for (Map.Entry<String, Long> entry : map.entrySet()) {
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

    /**
     * 直接排序
     */
    public static List<String> solution3(String[] arr, int k) {
        Map<String, Long> map = getCountMap(arr);
        return map.entrySet().stream()
                .sorted((o1, o2) -> (int) (o2.getValue() - o1.getValue()))
                .map(Map.Entry::getKey).limit(k)
                .collect(Collectors.toList());
    }

    private static Map<String, Long> getCountMap(String[] arr) {
        //Map<String, Integer> map = new HashMap<>();
        //for (String s : arr) {
        //    if (map.containsKey(s)) {
        //        map.put(s, map.get(s) + 1);
        //    } else {
        //        map.put(s, 1);
        //    }
        //}
        return Arrays.stream(arr).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}


@Data
@AllArgsConstructor
class WordEntity {
    private String word;
    private Long count;
}
