// https://leetcode.com/problems/reverse-string/
package String;

public class ReverseString {
    public char[] reverseString(char[] s) {
        int L = 0;
        int R = s.length - 1;
        while (L < R) {
            char t = s[L];
            s[L] = s[R];
            s[R] = t;
            L++;
            R--;
        }
        return s;
    }
}
