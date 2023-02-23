// https://leetcode.com/problems/valid-parentheses/
package Stack;

import java.util.Stack;

public class ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[') {
                stack.push(']');
            } else if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()(){}";
        System.out.println(isValid(s));
    }
}
