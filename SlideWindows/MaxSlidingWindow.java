// https://leetcode.com/problems/sliding-window-maximum/
package SlideWindows;

import java.util.*;

public class MaxSlidingWindow {

    static class AComp implements Comparator<int[]> {
        public int compare(int[] o1, int[] o2) {
            return o2[0] - o1[0];
        }
    }

    /**
     * 窗口中的值用大根堆维护  时间复杂度是O(NLogN)  稍微比N方快一些
     * @param nums  num
     * @param k  k
     * @return  k
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        int[] res = new int[nums.length - k + 1];
        PriorityQueue<int[]> heap = new PriorityQueue<>(new AComp());
        // 先加入前k个元素
        for (int i = 0; i < k; i++) {
            heap.add(new int[] {nums[i], i});
        }
        // 窗口右移
        res[0] = heap.peek()[0];
        for (int i = k; i < nums.length; i++) {
            heap.add(new int[] {nums[i], i});
            while (heap.peek()[1] < i - k + 1) {
                heap.poll();
            }
            res[i - k + 1] = heap.peek()[0];
        }
        return res;
    }

    // 使用单调队列
    // 队列中存储数组下标，下标是单调递增的，表示从左到右移动
    // 与此同时下标对应的值是单调递减的
    // 同时满足上述两个条件的队列是单调队列
    // 当假如一个元素的时候，如果队尾元素比他小，弹出队尾元素，直到队尾元素大于等于新元素或者队列为空
    // 窗口的最大值在队首，弹出的时候如果队首元素在窗口中则加入否则弹出
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        // 队列中存储数组下标
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 初始化前k个元素
        for (int i = 0; i < k; i++) {
            // 这里改成 <=
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.peekFirst()];
        // 窗口开始移动
        for (int i = k; i < nums.length; i++) {
            // 添加元素
            // 这里也是小于等于
            // 等于的情况下替换，因为下标大的永远比下标小的后出窗口
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            // 弹出元素
            // 这里也是小于等于
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            res[i - k + 1] = nums[deque.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,-1,-3,5,3,6,7};
        int[] res = maxSlidingWindow2(arr, 3);
        System.out.println(Arrays.toString(res));
    }
}
