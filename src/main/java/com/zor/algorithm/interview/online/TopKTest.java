package com.zor.algorithm.interview.online;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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

        List<String> test = getTopK(s, 3);
        System.out.println(test);

    }

    /**
     * 大文件里面有很多单次，找出重复最多的前10个单词
     */
    public static List<String> getTopK(String[] s, int k) {
        List<String> ret = new ArrayList<>();
        // 先遍历
        Map<String, Integer> map = new HashMap<>();
        for (String word : s) {
            if (map.containsKey(word)) {
                Integer count = map.get(word);
                count++;
                map.put(word, count);
            } else {
                map.put(word, 1);
            }
        }

        // 构建最小堆
        Queue<WordEntity> queue = new PriorityQueue<>(Comparator.comparingInt(WordEntity::getCount));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
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
}


@Data
@AllArgsConstructor
class WordEntity {
    private String word;
    private int count;
}
