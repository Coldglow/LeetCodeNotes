// https://leetcode.com/problems/ransom-note/
package HashTable;

import java.util.HashMap;

public class CanConstruct {
    // 还是统计词频  不过可以换成用数组
    // 数据量小的时候数组比map要节省空间
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ran = ransomNote.toCharArray();
        char[] mag = magazine.toCharArray();
        for (Character c : ran) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : mag) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
            if (map.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
