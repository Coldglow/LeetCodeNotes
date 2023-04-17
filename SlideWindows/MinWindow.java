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

    /*
        2023.04.12
     */
    public String minWindow2(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int validChar = 0, total = t.length();
        int left = 0, right = 0;
        int rLeft = -1, rRight = s.length();

        while (!(validChar < total && right == s.length())) {
            // right右移，扩充窗口
            while(validChar != total && right < s.length()) {
                // 查看right指向的字符是否在t中
                Integer val = map.get(s.charAt(right));
                // 如果val大于0，那么说明窗口中还缺少该字符，所以需要减去-1，validChar+1
                // 如果val小于等于0，说明窗口中多余该字符，继续减去-1，但是validChar不加1
                if (val != null) {
                    if (val > 0) {
                        validChar += 1;
                    }
                    map.put(s.charAt(right), val - 1);
                }
                right += 1;
            }
            // left右移，减小窗口
            while (validChar == total) {
                Integer val = map.get(s.charAt(left));
                // 非t中的字符
                if (val == null) {
                    left += 1;
                    continue;
                }
                if(right - left < rRight - rLeft) {   // 不存在多余的字符,记录一个答案
                    rLeft = left;
                    rRight = right;
                }
                if (val == 0) {
                    validChar -= 1;
                }
                // 无论是否多余，都要加上1
                map.put(s.charAt(left), val + 1);
                left += 1;
            }
        }
        return rLeft == -1 ? "" : s.substring(rLeft, rRight);
    }

    public static void main(String[] args) {
        MinWindow min = new MinWindow();
        System.out.println(min.minWindow2("ADOBECODEBANC", "ABC"));
    }
}
