package Youyong;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 07:21
 */
public class IsSquareValid {
    public static boolean isValid (String brackets) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = brackets.toCharArray();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (char c : chars) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty() || stack.pop() != map.get(c)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(){}[ ]";
        System.out.print(isValid(s));
    }
}
