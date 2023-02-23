// https://leetcode.com/problems/next-greater-element-ii/
package Stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    // !!!!!!!!!!!!!!!!  错误 有bug
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 1) {
            return new int[] {-1};
        }
        int n = nums.length;
        int[] res = new int[n];
        // 栈中记下标
        Stack<Integer> inStack = new Stack<>();
        Stack<Integer> ouStack = new Stack<>();
        int max = -1;
        // 将左侧的值按照从大到小的顺序压入outStack中
        for (int i = 0; i < n; i++) {
            while (!inStack.isEmpty() && nums[i] > nums[inStack.peek()]) {
                // 记录右侧最近最大值
                int index = inStack.pop();
                res[index] = nums[i];
                // 进入outStack
                if (ouStack.isEmpty() || nums[index] >= nums[ouStack.peek()]) {
                    ouStack.push(index);
                }
            }
            if (inStack.isEmpty()) {
                max = i;
            }
            inStack.push(i);
        }
        // 循环结束后inStack依次弹出
        // 正常来说此时inStack中的值没有右侧较大值，但是因为这里是首尾相接的
        // 所以可以从outStack中寻找
        // 此时如果inStack顶层元素比outStack所有元素都大
        // 说明比inStack栈顶元素大的值在inStack中，并且就是他的下一层
        while (!inStack.isEmpty()) {
            int inIndex = inStack.pop();
            // 弹出outStack中小于inStack栈顶的元素
            if (!ouStack.isEmpty() && nums[inIndex] < nums[ouStack.peek()]) {
                // outStack中存在比inStack栈顶元素大的值
                res[inIndex] = nums[ouStack.peek()];
            } else if (!inStack.isEmpty() && nums[max] > nums[inIndex]) {
                // 如果outStack中不存在，那么就是inStack的最底层元素
                res[inIndex] = nums[max];
            } else {
                res[inIndex] = -1;
            }
        }
        return res;
    }

    // 过两遍数组
    public int[] nextGreaterElements2(int[] nums) {
        if (nums.length == 1) {
            return new int[] {-1};
        }
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n - 1; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }
}
