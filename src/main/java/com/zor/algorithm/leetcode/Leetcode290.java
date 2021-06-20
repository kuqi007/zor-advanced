package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by kuqi0 on 2020/12/27
 * No.290 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Leetcode290 {

    public static void main(String[] args) {

        System.out.println(solution1("abba", "dog cat cat dog"));

    }

    /**
     * 这种解法最通俗易懂
     */
    public static boolean solution1(String pattern, String s) {
        int length = pattern.length();
        String[] words = s.split(" ");
        if (length != words.length) {
            return false;
        }
        Map<Character, String> mapS = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            if (mapS.containsKey(c)) {
                // key不存在
                if (!mapS.get(c).equals(word)) {
                    return false;
                }
            } else {
                //key不存在
                // 两个value的值一样 a-dog b-dog->false
                if (mapS.containsValue(word)) {
                    return false;
                }
                mapS.put(c, word);
            }
        }
        return true;
    }


    public static boolean wordPattern(String pattern, String s) {

        int length = pattern.length();
        String[] split = s.split(" ");
        if (split.length != length) {
            return false;
        }

        Map<Character, String> mapPattern = new HashMap<>();
        Map<String, Character> mapS = new HashMap<>();


        for (int i = 0; i < length; i++) {
            char p = pattern.charAt(i);
            String word = split[i];

            if ((mapPattern.containsKey(p) && !mapPattern.get(p).equals(word))
                    || (mapS.containsKey(word) && mapS.get(word) != p)) {
                return false;
            }
            mapPattern.put(p, word);
            mapS.put(word, p);
        }
        return true;
    }

    /**
     * 第二种方法：一个 HashMap 即可。大概思路如下：
     * 1：我们只管 pattern 与 s 之间字符一一对应即可，看一个例子 pattern=abba，s=dog cat cat dog；第一次调用 map 的 put 方法时返回 null，如果是第 n 次调用的话即返回第 n-1 次的 value，很明显 valua 至关重要，因为 pattern 与 s 的长度是相等的，所以数组下标 i 是一一对应的，我们用 i 做 map 的 value 即可；
     * 2：所以我们直接遍历 pattern 就可以了，第一次 map.put(a)跟 map.put(dog)都是返回 null，第二次 map.put(a)跟 map.put(dog)都是返回 0，以此类推下去即可；
     * 3：这种解决问题的方法的重点在于利用两个字符串的下标是一一对应的，其次则是利用put的返回值；
     * 代码如下
     * <p>
     * 作者：zheng-rong-huai
     * 链接：https://leetcode-cn.com/problems/word-pattern/solution/liang-chong-si-lu-9894-9853-by-zheng-ron-4x63/
     */
    private static boolean solution2(String pattern, String s) {
        int length = pattern.length();
        String[] split = s.split(" ");
        if (split.length != length) {
            return false;
        }
        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            // 利用map.put方法的返回值（上一次的value）
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(split[i], i))) {
                return false;
            }
        }
        return true;
    }

}
