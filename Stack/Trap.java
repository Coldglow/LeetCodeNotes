// https://leetcode.com/problems/trapping-rain-water/
package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Trap {
    // 栈求法
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int water = 0;
        int index = 0;
        while (index < height.length) {
            while (!stack.isEmpty() && height[index] > height[stack.peek()]) {
                int h = height[stack.pop()];  // 注意这里直接pop
                if (stack.isEmpty()) { // 防止左边为空的情况
                    break;
                }
                int distance = index - stack.peek() - 1;
                // 和当前元素的上个元素比较大小，选择较小的那个作为短板
                // 因为栈从大到小的顺序存储，因此pop之后，peek的值一定比pop的值大
                // 所以只需要将peek的值和当前值比较，就可以得出夹在中间的元素左右两边墙哪个小了
                int minHeight = Math.min(height[index], height[stack.peek()]);  // 这里是peek而非pop
                water += (minHeight - h) * distance;
            }
            stack.push(index);
            index++;
        }
        return water;
    }

    // 按照列的方法求
    // 得到每一列左侧和右侧最高的高度
    // 然后当前列可以存储的水就是两侧高度较小值减去当前列的高度
    public int trap2(int[] height) {
        int n = height.length;
        int water = 0;
        int[] max_left = new int[n];
        int[] max_right = new int[n];

        for (int i = 1; i < n; ++i) {
            max_left[i] = Math.max(height[i - 1], max_left[i - 1]);
        }
        for (int i = n - 2; i > -1; --i) {
            max_right[i] = Math.max(height[i + 1], max_right[i + 1]);
        }

        for (int i = 0; i < n; ++i) {
            int min = Math.min(max_left[i], max_right[i]);
            if (height[i] < min) {
                water += (min - height[i]);
            }
        }
        return water;
    }


    /*
        2023.03.27
        用两个数组求
     */

    public int trap_03(int[] height) {
        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = -1;
        int maxLeft = Math.max(height[0], -1);
        int maxRight = Math.max(height[n - 1], -1);
        for (int i = 1, j = n - 2; i < n && j > -1; i++, j--) {
            left[i] = maxLeft < height[i] ? -1 : maxLeft;
            right[j] = maxRight < height[j] ? -1 : maxRight;
            maxLeft = Math.max(maxLeft, height[i]);
            maxRight = Math.max(maxRight, height[j]);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int temp = Math.min(left[i], right[i]);
            res += temp < 0 ? 0 : temp - height[i];
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {4,2,0,3,2,5};
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        Trap o = new Trap();
        System.out.print(o.trap_03(arr));
    }
}
