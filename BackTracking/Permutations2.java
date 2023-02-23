// https://leetcode.com/problems/permutations-ii/
package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations2 {
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> temp = new LinkedList<>();
    private int[] used;  // 整体用  也可以认为是一条路径用一个

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new int[nums.length];
        backTracking5(nums);
        return this.res;
    }

    public void backTracking5(int[] nums) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        int[] used2 = new int[21];  // 每一层用一个  用于纵向去重
        for (int i = 0; i < nums.length; ++i) {
            if (used2[10 + nums[i]] == 1 || used[i] == 1) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = 1;
            used2[10 + nums[i]] = 1;
            backTracking5(nums);
            used[i] = 0;
            temp.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 2};
        Permutations2 o = new Permutations2();
        List<List<Integer>> res = o.permuteUnique(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
