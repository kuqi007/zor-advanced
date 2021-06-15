package com.zor.algorithm.interview.online.alibaba;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021/6/16 阿里笔试
 * 题目1.字符串匹配
 * 有一个字符串它的构成是词+空格的组合，如“北京 杭州 杭州 北京 上海”， 要求输入一个匹配模式（简单的以字符来写）， 比如 aabb, 来判断该字符串是否符合该模式， 举个例子：
 * 1. pattern = "abbac", str="北京 杭州 杭州 北京 上海" 返回 ture
 * 2. pattern = "aacbb", str="北京 北京 上海 杭州 北京" 返回 false
 * 3. pattern = "baabcc", str="北京 杭州 杭州 北京 上海 上海" 返回 true
 * Created by kuqi0 on 2021/6/15
 */

public class WordPattern {
    public static void main(String[] args) {
        boolean res = wordPattern("abba", "dog cat cat fish");
        boolean res1 = wordPattern(null, null);
        boolean res2 = wordPattern("null", "null");
        boolean res3 = wordPattern("abbac", "北京 北京 上海   杭州 北京");
        System.out.println(res);
    }

    public static boolean wordPattern(String pattern, String s) {
        // 输入为空
        if (pattern == null || s == null) return false;

        String[] words = s.split(" ");
        if (pattern.length() == 0 && words.length == 0) {
            return true;
        }
        // 长度不匹配
        if (pattern.length() != words.length) {
            return false;
        }
        // 避免扩容
        Map<Character, String> mapP = new HashMap<>();
        Map<String, Character> mapWord = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);

            if ((mapP.containsKey(c) && !words[i].equals(mapP.get(c))) || (mapWord.containsKey(words[i]) && !mapWord.get(words[i]).equals(c))) {
                return false;
            }

            mapP.put(c, words[i]);
            mapWord.put(words[i], c);

        }
        return true;
    }

    @Test
    public void test() {
        Assertions.assertFalse(wordPattern("", ""));
        Assertions.assertFalse(wordPattern(null, null));
        Assertions.assertFalse(wordPattern("abbac", "北京 北京 上海   杭州 北京"));
        Assertions.assertTrue(wordPattern("aabca", "北京 北京 上海 杭州 北京"));
    }


}
