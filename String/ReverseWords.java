// https://leetcode.com/problems/reverse-words-in-a-string/
package String;

public class ReverseWords {
    /**
     * 从后往前遍历，利用StringBuilder的append方法
     * 可以在不需要知道字符串长度的情况下添加字符
     * @param s s
     * @return s
     */
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int n = 0;
        boolean needSpace = false;
        // 要等于-1，否则如果开头没有空格，不会添加第一个单词
        for (int i = s.length() - 1; i >= -1; i--) {
            if (i > -1 && s.charAt(i) != ' ') {
                n++;
            } else if (n != 0) {
                // 每次清算的时候先看看需要不需要添加空格
                if (needSpace) {
                    sb.append(' ');
                } else {
                    needSpace = true;
                }
                int k = 1;
                while (k <= n) {
                    sb.append(s.charAt(i + k));
                    k++;
                }
                n = 0;
            }
        }
        System.out.println(s.length());
        return new String(sb);
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        ReverseWords o = new ReverseWords();
        System.out.println(o.reverseWords(s));
    }
}
