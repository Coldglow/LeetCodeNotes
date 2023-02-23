// https://leetcode.com/problems/remove-k-digits/
package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
    // 这里只能保存字符 不能保存数字  否则会溢出
    private Deque<Character> deque = new ArrayDeque<>();

    /**
     * 做单调栈的题的时候，一定要确定栈顶到栈底是什么顺序再下手
     * @param num  num
     * @param k  k
     * @return  int
     */
    public String removeKdigits(String num, int k) {
        // 如果k >= num 长度 直接返回0
        if (k >= num.length()) {
            return "0";
        }
        char[] ch = num.toCharArray();
        for (int i = 0; i < num.length(); ++i) {
            // 如果栈顶元素大于当前元素，栈顶元素弹出
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > ch[i]) {
                deque.pollLast();
                k--;
            }
            deque.addLast(ch[i]);
        }
        // 清算，如果k > 0，弹出栈顶元素
        while (!deque.isEmpty() && k > 0) {
            k--;
            deque.pollLast();
        }
        // 去掉前置0
        while (!deque.isEmpty() && deque.peekFirst() == '0') {
            deque.pollFirst();
        }
        // 计算结果
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String num = "5337";
        int k = 2;
        RemoveKDigits o = new RemoveKDigits();
        System.out.println(o.removeKdigits(num, k));
    }
}
