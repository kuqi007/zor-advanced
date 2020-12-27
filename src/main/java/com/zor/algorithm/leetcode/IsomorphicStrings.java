package com.zor.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by kuqi0 on 2020/12/27
 * No.205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        boolean isomorphic = isIsomorphic("paper", "title");
        System.out.println(isomorphic);

    }

    public static boolean isIsomorphic(String s, String t) {

        Map<Character, Character> mapS = new HashMap<>();

        Map<Character, Character> mapT = new HashMap<>();

        int length = s.length();

        for (int i = 0; i < length; i++) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((mapS.containsKey(x) && mapS.get(x) != y) || (mapT.containsKey(y) && mapT.get(y) != x)) {
                return false;
            }
            mapS.put(x, y);
            mapT.put(y, x);
        }
        return true;
    }

    /**
     * 此解法效率要高于上面那一种
     */
    public static boolean solution2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean solution3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!Objects.equals(map.put(String.valueOf(s.charAt(i)), i), map.put(t.charAt(i), i))) {
                return false;
            }
        }
        return true;
    }
}
