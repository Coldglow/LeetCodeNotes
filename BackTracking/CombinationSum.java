// https://leetcode.com/problems/combination-sum/
package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> oneAns = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combine(0, 0, target, candidates);
        return this.res;
    }

    // 卡在了怎么去重
    public void combine(int curSum, int i, int target, int[] candidates) {
        if (curSum == target) {
            this.res.add(new ArrayList<>(this.oneAns));
            return;
        }

        for (; i < candidates.length; ++i) {
            if (candidates[i] > target || candidates[i] > target - curSum) {
                return;
            }
            this.oneAns.add(candidates[i]);
            combine(curSum + candidates[i], i, target, candidates);
            this.oneAns.remove(this.oneAns.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,3,6,7};
        CombinationSum o = new CombinationSum();
        List<List<Integer>> res = o.combinationSum(arr, 7);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
