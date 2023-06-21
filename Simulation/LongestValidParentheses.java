package Simulation;

import java.util.LinkedList;

/**
 * @author David Wong
 * @date 17/04/2023 22:10
 * Scanner in = new Scanner(System.in);
 */
public class LongestValidParentheses {
    /*
    栈中记录下标，当遍历完成后，留在栈中的数字就是数组的分割点，找出最长的那段就行
     */
    public int longestValidParentheses(String s) {
        char[] ch = s.toCharArray();
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                stack.push(i);
            } else if (stack.isEmpty()){
                stack.push(i);
            } else if (ch[stack.peek()] == '('){
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        // 如果完全配对，直接返回数组长度
        if (stack.isEmpty()) {
            return ch.length;
        }
        // 如果栈中剩下下标，说明不是完全配对
        int pos = stack.pop();
        int res = ch.length - pos - 1;
        while (!stack.isEmpty()) {
            res = Math.max(res, pos - stack.peek() - 1);
            pos = stack.pop();
        }
        res = Math.max(res, pos);
        return res;
    }

    public static void main(String[] args) {
        LongestValidParentheses o = new LongestValidParentheses();
        int res = o.longestValidParentheses("(()");
        System.out.println(res);
    }
}
