// https://leetcode.com/problems/implement-queue-using-stacks/
package Stack;

import java.util.Stack;

public class ImplementQueueWIthStacks {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public ImplementQueueWIthStacks() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    public void push(int x) {
        // 这里直接push，因为在pop和peek的时候会把outStack的元素压回去
        this.inStack.push(x);
    }

    public int pop() {
        return this.outStack.pop();
    }

    public int peek() {
        return this.outStack.peek();
    }

    public boolean empty() {
        return this.outStack.isEmpty() && this.inStack.isEmpty();
    }
}
