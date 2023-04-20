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

    /*
    2023.04.20
    局部反转 + 整体反转
    还得去除单词之间多余的空格
     */
    public String reverseWords2(String s) {
        // 去除首尾空格
        s = s.trim();
        // 去除中间多余的空格
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && sb.charAt(sb.length() - 1) != ' ') {
                sb.append(' ');
            } else if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }
        char[] ch = sb.toString().toCharArray();
        // 整体翻转
        reverse(ch, 0, sb.length() - 1);
        // 局部翻转
        int left = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                reverse(ch, left, i - 1);
                left = i + 1;
            }
            if (i == ch.length - 1) {
                reverse(ch, left, i);
            }
        }
        return new String(ch);
    }

    /*
    翻转ch[left, right]部分的字符
     */
    public void reverse(char[] ch, int left, int right) {
        while (left < right) {
            char temp = ch[left];
            ch[left] = ch[right];
            ch[right] = temp;
            left += 1;
            right -= 1;
        }
    }

    public static void main(String[] args) {
        String s = "  the sky   is blue  ";
        ReverseWords o = new ReverseWords();
//        System.out.println(o.reverseWords(s));
        String res = o.reverseWords2(s);
        System.out.println(res);
    }
}
