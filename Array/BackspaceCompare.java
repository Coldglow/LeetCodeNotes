package Array;

import java.util.Arrays;
import java.util.Stack;

public class BackspaceCompare {
    /**
     * 用栈比较  O(n+m)
     * @param s  s
     * @param t  t
     * @return   f
     */
    public boolean backspaceCompare1(String s, String t) {
        int s1 = 0;
        int s2 = 0;
        while (s.charAt(s1) == '#') {
            s1++;
        }
        while (t.charAt(s2) == '#') {
            s2++;
        }
        Stack<Character> stack = new Stack<>();
        while (s1 < s.length()) {
            if (s.charAt(s1) != '#') {
                stack.push(s.charAt(s1));
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
            s1++;
        }
        String str1 = stack.toString();
        stack.clear();
        while (s2 < t.length()) {
            if (t.charAt(s2) != '#') {
                stack.push(t.charAt(s2));
            } else if (!stack.isEmpty()) {
                stack.pop();
            }
            s2++;
        }
        String str2 = stack.toString();
        return str1.equals(str2);
    }

    public int ps, pt, skipS, skipT;
    public boolean backspaceCompare2(String s, String t) {
        ps = s.length() - 1;
        pt = t.length() - 1;
        skipS = 0;
        skipT = 0;
        while (ps > -1) {
            if (s.charAt(ps) == '#') {
                skipS++;
            } else if (skipS != 0) {
                skipS--;
            } else {
                if (!compare(s, t)) {
                    return false;
                } else {
                    pt--;
                }
            }
            ps--;
        }
        // 如果s走完，但是t没走完的情况
        while(pt > -1) {
            if (t.charAt(pt) == '#') {
                skipT++;
            } else if (skipT != 0) {
                skipT--;
            } else {
                return false;
            }
            pt--;
        }
        return true;
    }

    public boolean compare(String s, String t) {
        if (pt < 0) {
            return false;
        }
        while (pt > -1) {
            if (t.charAt(pt) == '#') {
                skipT++;
            } else if (skipT != 0) {
                skipT--;
            } else {
                return s.charAt(ps) == t.charAt(pt);
            }
            pt--;
        }
        return true;
    }

    public static void main(String[] args) {

        BackspaceCompare obj = new BackspaceCompare();
        System.out.println(obj.backspaceCompare2("bxj##tw", "bxj###tw"));
    }
}
