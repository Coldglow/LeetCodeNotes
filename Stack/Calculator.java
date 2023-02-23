// https://leetcode.cn/problems/basic-calculator-ii/
package Stack;

import java.util.Stack;

public class Calculator {
    /**
     * 用一个preSign来保存前一个符号
     * 对于每次遍历遇到数字的情况
     * 先计算数字
     * 然后根据preSign来计算
     * preSign: +  计算出来的数字直接入栈  因为最后是要相加的
     * -:  相反数输入栈
     * *:  乘的结果入栈
     * /： 除的结果入栈
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char preSign = '+';
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }

            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                System.out.println(s.charAt(i));
                System.out.println(preSign);
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        num = stack.pop() * num;
                        stack.push(num);
                        break;
                    default:
                        num = stack.pop() / num;
                        stack.push(num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }

        // 结束后栈中的所有数相加即可
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "3*2-2";
        Calculator obj = new Calculator();
        System.out.print(obj.calculate(s));
    }
}
