// https://leetcode.com/problems/evaluate-reverse-polish-notation/
package Stack;

import java.util.Stack;

public class EvalRPN {
    // 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        String num1, num2;
        for (String token : tokens) {
            if (token.equals("*") || token.equals("/") || token.equals("-") || token.equals("+")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.push(calculate(token, num1, num2));
            } else {
                stack.push(token);
            }
        }
        return toDigit(stack.pop());
    }

    public int toDigit(String s) {
        int num = 0;
        if (s.charAt(0) == '-') {
            for (int i = 1; i < s.length(); i++) {
                num = num * 10 + s.charAt(i) - '0';
            }
            return -num;
        } else {
            for (int i = 0; i < s.length(); i++) {
                num = num * 10 + s.charAt(i) - '0';
            }
            return num;
        }
    }

    // 计算
    public String calculate(String sign, String s1, String s2) {
        int num1 = toDigit(s1);
        int num2 = toDigit(s2);
        switch (sign) {
            case "*":
                return Integer.toString(num1 * num2);
            case "-":
                return Integer.toString(num1 - num2);
            case "+":
                return Integer.toString(num1 + num2);
            default:
                return Integer.toString(num1 / num2);
        }
    }
}
