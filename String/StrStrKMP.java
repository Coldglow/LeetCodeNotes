// https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
package String;

import java.util.Arrays;

public class StrStrKMP {
    // KMP  求出str2的最长前缀数组
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        char[] ch1 = haystack.toCharArray();
        char[] ch2 = needle.toCharArray();
        int[] next = getNext(ch2);
        int i1 = 0;
        int i2 = 0;
        System.out.println(Arrays.toString(next));
        while (i1 < ch1.length && i2 < ch2.length) {
            if (ch1[i1] == ch2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == ch2.length ? i1 - i2 : -1;
    }

    /**
     * 人为规定0位置是-1   1位置是0
     * @param str str
     * @return f
     */
    public int[] getNext(char[] str) {
        if (str.length == 1) {
            return new int[] {-1};
        }

        int len = str.length;
        int[] next = new int[len];
        next[0] = -1;
        next[1] = 0;
        int i = 2;  // i是遍历的下标
        int cn = 0;  // cn是i-1位置的字符的前缀长度 也是前缀下一个字符的下标
        while (i < str.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

    public static void main(String[] args) {
        String s1 = "mississippi";
        String s2 = "abcabcabc";
        StrStrKMP o = new StrStrKMP();
        o.getNext(s2.toCharArray());
//        System.out.println();
    }
}
