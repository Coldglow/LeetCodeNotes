/**
 * https://leetcode.cn/problems/permutation-in-string/
 */
package SlideWindows;

import java.util.HashMap;
import java.util.Objects;

public class CheckInclusion {


    HashMap<Character, Integer> map1;
    HashMap<Character, Integer> map2;

    /**
     * 固定窗口大小为s1的长度
     * 从左到右遍历s2，进一个出一个，然后检查窗口中每个字符的个数是否于s1中的相等
     * 是则返回，否就继续遍历
     * @param s1 短的
     * @param s2 长的
     * @return true  or false
     */
    public boolean checkInclusion(String s1, String s2) {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        int len1 = s1.length();
        int index = 0;
        while(index < s2.length()) {
            if (index >= len1) {
                // 左侧的出去
                map2.put(s2.charAt(index - len1), map2.getOrDefault(s2.charAt(index - len1), 0) - 1);
            }
            map2.put(s2.charAt(index), map2.getOrDefault(s2.charAt(index), 0) + 1);
            index++;
            if (check()) {
                return true;
            }
        }
        return false;
    }

    public boolean check() {
        if (map2.size() < map1.size()) {
            return false;
        }
        for (Character c : map1.keySet()) {
            if (!Objects.equals(map2.getOrDefault(c, 0), map1.get(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 上面的方法每次进出一个数都要比较
     * 可以优化成用一个数记录窗口中的字符和s1中不同字符的个数
     * 如果cnt[b] == 1，表明窗口中多出一个b
     * 如果cnt[c] == -1 表明窗口中少一个c
     * 遍历cnt，如果cnt全是0，说明正好
     * @param s1 s1
     * @param s2 s2
     * @return true or false
     */
    public boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] cnt = new int[26];   // 仅包含小写字母
        int diff = 0;

        // 初始化cnt数组，s2的前n个字符和s1比较  n是s1的长度
        // 如果s1中出现了，cnt中对应位置-- 表明缺少一个
        // 如果s1中出现了，cnt中对应位置++ 表明出现一个
        for (int i = 0; i < len1; i++) {
            cnt[s1.charAt(i) - 'a']--;
            cnt[s2.charAt(i) - 'a']++;
        }
        // 遍历cnt数组，哪个位置不为0，diff相加    错！！！！！！！！！！！！！
        // 不是那个位置不为0，diff相加，  是哪个位置不为零，diff加1
        for (int n : cnt) {
            if (n != 0) {
                diff++;
            }
        }
        // 如果diff == 0 说明已经存在直接返回
        if (diff == 0) {
            return true;
        }
        // diff不为0，窗口右移，进来x，出去y
        for (int i = len1; i < len2; i++) {
            // 减去'a'求出在cnt中的下标
            int x = s2.charAt(i) - 'a';
            int y = s2.charAt(i - len1) - 'a';
            // 如果x和y相同，直接跳过
            if (x == y) {
                continue;
            }
            // x和y不同，x位置现在如果是0，说明窗口中和s1中x的个数相同
            // 现在要进来一个，会多出一个，所以diff++
            if (cnt[x] == 0) {
                diff++;
            }
            // x位置++
            cnt[x]++;
            // 如果x位置加一之后变成了0
            // 说明之前窗口中少一个x，加上之后正好相等
            // 那么diff减1
            if (cnt[x] == 0) {
                diff--;
            }
            // 对于要出去的y同理，不过和x增减相反
            if (cnt[y] == 0) {
                diff++;   // 少一个y
            }
            cnt[y]--;
            if (cnt[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckInclusion obj = new CheckInclusion();
        System.out.println(obj.checkInclusion2("hello", "oooolloeoolh"));
    }
}
