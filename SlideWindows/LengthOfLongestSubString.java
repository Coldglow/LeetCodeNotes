package SlideWindows;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串中不重复的最长子串
 */
public class LengthOfLongestSubString {

    /**
     * 暴力递归想不出来，看答案说用滑动窗口解决
     * 从左到右遍历，如果set当中有下一个字符，就把最左边的字符弹出，如果还有，继续弹出直到set为空或者没有
     * 维护两个指针分别指向窗口的左右两端，当需要弹出的时候，左指针指向的字符弹出，左指针右移
     * @param s  str
     * @return   n
     */
    public static int getLongestSubString(String s) {

        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(s.charAt(right));
            maxLength = Math.max(maxLength, window.size());
        }
        return maxLength;
    }

    // 第二次自己写的代码
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        char[] ch = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int res = 0;
        int left = 0;
        int right = 0;
        while (right < ch.length) {
            // 如果set不包含
            if (set.contains(ch[right])) {
                // set包含，left位置开始去除
                while (set.contains(ch[right])) {
                    set.remove(ch[left]);
                    left++;
                }
            }
            set.add(ch[right]);
            res = Math.max(res, set.size());
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = " ";
        System.out.println(getLongestSubString(s));
    }
}
