// https://leetcode.cn/problems/minimum-window-substring/
package SlideWindows;

import java.util.HashMap;

public class MinWindow {
    private HashMap<Character, Integer> hashT;
    private HashMap<Character, Integer> hashS;

    /**
     * L初始0 R初始-1
     * 把t中的字符和词频记录在一个哈希表中
     * R右移，当窗口中的字符能够包含t时，就记录L R
     * @param s f
     * @param t f
     * @return f
     */
    public String minWindow(String s, String t) {
        hashS = new HashMap<>();
        hashT = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            hashT.put(t.charAt(i), hashT.getOrDefault(t.charAt(i), 0) + 1);
        }

        int L = 0; int R = -1; int curLen = Integer.MAX_VALUE;
        int ansL = -1; int ansR = 1;
        while (R < s.length()) {
            R++;
            // 如果hashT中包含，hashS中加一
            if (R < s.length() && this.hashT.containsKey(s.charAt(R))) {
                this.hashS.put(s.charAt(R), this.hashS.getOrDefault(s.charAt(R), 0) + 1);
            }
            // 够了L就左移
            while (check() && L <= R) {
                // 记录一个答案
                if (curLen > (R - L + 1)) {
                    ansL = L;
                    ansR = R + 1;
                    curLen = R - L + 1;
                }
                if (this.hashT.containsKey(s.charAt(L))) {
                    this.hashS.put(s.charAt(L), this.hashS.getOrDefault(s.charAt(L), 0) - 1);
                }
                L++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        if (this.hashS.size() < this.hashT.size()) {
            return false;
        }

        for (Character key : this.hashS.keySet()) {
            if (this.hashS.get(key) < this.hashT.get(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 可以用一个数来记录当前窗口中属于t中字符的个数，这样hashS就不是必须了
     * 注意当窗口中单个字符的出现次数 大于 t中的出现次数   distance不再加一
     * 如何记录单个字符出现的次数？
     * 没改对，有bug
     * 当输入  bba  ba的时候出错
     * @param s d
     * @param t f
     * @return  g
     */
    public String minWindow1(String s, String t) {
        hashT = new HashMap<>();

        int lenT = t.length();
        for (int i = 0; i < t.length(); i++) {
            hashT.put(t.charAt(i), hashT.getOrDefault(t.charAt(i), 0) + 1);
        }

        int L = 0; int R = -1; int curLen = Integer.MAX_VALUE;
        int ansL = -1; int ansR = 1;
        int distance = 0;  // 记录窗口中属于t的字符的个数
        while (R < s.length()) {
            R++;
            // 如果hashT中包含，hashS中加一
            if (R < s.length() && this.hashT.containsKey(s.charAt(R))) {
                distance++;
            }
            // 够了L就左移
            while (distance == lenT && L <= R) {
                // 记录一个答案
                if (curLen > (R - L + 1)) {
                    ansL = L;
                    ansR = R + 1;
                    curLen = R - L + 1;
                }
                if (this.hashT.containsKey(s.charAt(L))) {
                    distance--;
                }
                L++;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }


    public static void main(String[] args) {
        MinWindow min = new MinWindow();
        System.out.println(min.minWindow1("ADOBECODEBANC", "ABC"));
    }
}
