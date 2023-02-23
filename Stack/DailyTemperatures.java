// https://leetcode.com/problems/daily-temperatures/
package Stack;

import java.util.LinkedList;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; ++i) {
            // 栈为空后者栈顶元素大于等于当前元素
            if (stack.isEmpty() || temperatures[stack.peek()] >= temperatures[i]) {
                stack.push(i);
                continue;
            }
            // 栈顶元素值小于当前元素
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {30,40,50,60};
        DailyTemperatures o = new DailyTemperatures();
        int[] res = o.dailyTemperatures(arr);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
