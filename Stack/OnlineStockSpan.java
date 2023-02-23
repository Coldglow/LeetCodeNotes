// https://leetcode.com/problems/online-stock-span/
package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class OnlineStockSpan {
}

class StockSpanner {
    private int n;
    private Deque<int[]> stack;

    /**
     * 栈中存储日期
     */
    public StockSpanner() {
        this.n = 1;
        this.stack = new ArrayDeque<>();
        stack.push(new int[] {0, 100001});
    }

    public int next(int price) {
        while (!stack.isEmpty() && stack.peek()[1] <= price) {
            stack.pop();
        }
        int span = n - stack.peek()[0];
        stack.push(new int[] {n, price});
        n++;
        return span;
    }
}
