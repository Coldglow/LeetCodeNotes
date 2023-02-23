// https://leetcode.cn/problems/repeated-substring-pattern/
package String;

import java.util.Arrays;

public class RepeatedSubstringPattern {
    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return false;
        }
        int len = s.length();
        int[] next = new int[len];
        next[0] = -1;
        int i = 1;
        int cn = 0;
        while (i < len) {
            if (s.charAt(i) == s.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        System.out.println(Arrays.toString(next));
        return len % (len - next[len - 1]) == 0;
    }

    public static boolean getNext2(String pattern) {
        int n = pattern.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = fail[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        System.out.println(Arrays.toString(fail));
        return fail[n - 1] != -1 && n % (n - fail[n - 1] - 1) == 0;

    }

    public static void main(String[] args) {
        String s = "ababba";
        System.out.println(repeatedSubstringPattern(s));
        getNext2(s);
    }
}
