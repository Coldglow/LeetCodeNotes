package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<Integer>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 1, 1};
        Solution o = new Solution();
        List<List<Integer>> res = o.findSubsequences(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}

/**
 * 1 2
 * 1 2 3
 * 1 2 3 4
 * 1 2 4
 * 1 3
 * 1 3 4
 * 1 4
 * 1 1
 * 1 1 1
 * 2 3
 * 2 3 4
 * 2 4
 * 3 4
 * 1 1
 */