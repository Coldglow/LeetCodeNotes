// https://leetcode.com/problems/remove-duplicate-letters/
package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveDuplicateLetters {
    /**
     * 先遍历一遍统计词频
     * 然后第二次遍历，如果用过了，直接词频减一
     * 如果没用过，判断是否比栈顶元素小
     *      如果比栈顶元素小，并且栈顶元素此时的词频数大于0，栈顶元素弹出，否则栈顶元素不弹出
     *      append当前元素
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int[] num = new int[26];
        // 先遍历一边记录每个字符出现的次数
        for (char c : chars) {
            num[c - 'a']++;
        }
        // 记录字符是否用过
        boolean[] used = new boolean[26];
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            // 如果没用过
            if (!used[c - 'a']) {
                // 如果sb存在元素并且最后一个元素大于当前元素
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                    // 如果最后一个元素再在c字符之后还有
                    // 最后一个元素词频数减去1，从栈顶弹出
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        used[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                sb.append(c);
                used[c - 'a'] = true;
            }
            num[c - 'a']--;
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        String s = "bcabc";
        RemoveDuplicateLetters o = new RemoveDuplicateLetters();
        System.out.print(o.removeDuplicateLetters(s));
    }
}
