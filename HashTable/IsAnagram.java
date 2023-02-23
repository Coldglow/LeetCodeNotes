// https://leetcode.cn/problems/valid-anagram/
package HashTable;

import java.util.HashMap;

public class IsAnagram {
    // 先遍历其中一个 记录词频
    // 然后再遍历另一个 如果不存在直接返回 存在就词频减1
    // 减到0时删除
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                return false;
            }
            map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            if (map.get(t.charAt(i)) == 0) {
                map.remove(t.charAt(i));
            }
        }
        return true;
    }
}
