package Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindUnsortedSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // cur > top
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
                if (deque.size() == 1) {
                    break;
                }
            }
            // cur <= top
            //      add
            deque.addLast(i);
        }
        return deque.size() == 1 ? 0 : deque.peekLast() - deque.peekFirst() + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,6,4,8,10,9,15};
        FindUnsortedSubarray o = new FindUnsortedSubarray();
        System.out.println(o.findUnsortedSubarray(arr));
    }
}
