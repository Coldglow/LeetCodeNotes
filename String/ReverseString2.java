// https://leetcode.com/problems/reverse-string-ii/
package String;

import java.util.Arrays;

public class ReverseString2 {
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        // 可以不用StringBuilder
        // 直接用char数组生成一个新的字符串即可
        return new String(arr);
    }

    public void reverse(char[] s, int L, int R) {
        while (L < R) {
            char t = s[L];
            s[L] = s[R];
            s[R] = t;
            L++;
            R--;
        }
    }
}
