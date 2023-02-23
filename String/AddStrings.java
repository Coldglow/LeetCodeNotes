// https://leetcode.cn/problems/add-strings/
package String;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 一、 2023.2.19
 */

public class AddStrings {
    public static String addStrings(String num1, String num2) {
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();
        int n1 = ch1.length - 1;
        int n2 = ch2.length - 1;
        Deque<Character> res = new LinkedList<>();
        int moreTen = 0;
        while (n1 >= 0 || n2 >= 0) {
            int a1 = n1 < 0 ? 0 : ch1[n1] - '0';
            int a2 = n2 < 0 ? 0 : ch2[n2] - '0';
            res.addLast(Character.forDigit((a1 + a2 + moreTen) % 10, 10));
            moreTen = (a1 + a2 + moreTen) / 10;
            n1--;
            n2--;
        }
        StringBuilder sb = new StringBuilder();
        if (moreTen == 1) {
            sb.append('1');
        }
        while (!res.isEmpty()) {
            sb.append(res.pollLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "0";
        String s2 = "9";
        System.out.println(addStrings(s1, s2));
    }
}
