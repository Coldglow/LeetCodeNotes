// https://leetcode.com/problems/non-decreasing-subsequences/
package BackTracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NonDecreasingSubsets {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> temp = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTracking3(0, Integer.MIN_VALUE, nums);
        return this.res;
    }

    public void backTracking3(int index, int lastVal, int[] nums) {
        // 使用一个used数组来记录每层有哪些数字用过，用过的就直接跳过
        // 这里纵向和横向的剪枝都是continue而不是return
        int[] used = new int[201];
        for (int i = index; i < nums.length; ++i) {
            if (used[nums[i] + 100] == 1 || nums[i] < lastVal) {
                continue;
            }
            this.temp.add(nums[i]);
            used[nums[i] + 100] = 1;
            if (this.temp.size() > 1) {
                this.res.add(new ArrayList<>(this.temp));
            }
            backTracking3(i + 1, nums[i], nums);
            this.temp.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 1, 1};
        NonDecreasingSubsets o = new NonDecreasingSubsets();
        List<List<Integer>> res = o.findSubsequences(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
