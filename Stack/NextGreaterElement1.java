// https://leetcode.com/problems/next-greater-element-i/
package Stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class NextGreaterElement1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[nums1.length];

        for (int j : nums2) {
            if (stack.isEmpty() || stack.peek() > j) {
                stack.push(j);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() < j) {
                map.put(stack.pop(), j);
            }
            stack.push(j);
        }

        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {2,4};
        int[] arr2 = new int[] {1,2,3,4};
        NextGreaterElement1 o = new NextGreaterElement1();
        int[] res = o.nextGreaterElement(arr1, arr2);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }
}
