// https://leetcode.com/problems/largest-rectangle-in-histogram/
package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleArea {
    /**
     * 单调栈要考虑的边界情况：
     * 1. 等于如何解决
     * 2. 栈底到栈顶是从小到大还是从大到小
     * 3. 需不需要左右两侧的pivot
     * 4. 遍历结束后是否需要清算直到栈空
     * 5. 是否需要数组记录左右两侧值
     * @param heights  123132132123
     * @return   123123132
     */
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 1) {
            return heights[0];
        }
        int len = heights.length;
        // 在数组左右两端添加pivot
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        // 添加pivot，无需进行判空
        stack.push(0);
        for (int i = 1; i < len; ++i) {
            while (heights[i] < heights[stack.peek()]) {
                // 先poll再peek
                area = Math.max(area, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }

        System.out.print(area);
        return area;
    }

    public static void main(String[] args) {
        int[] heights = new int[] {3,6,5,7,4,8,1,0};
        LargestRectangleArea o = new LargestRectangleArea();
        o.largestRectangleArea(heights);
    }
}
