// https://leetcode.com/problems/combination-sum-ii/
package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> oneAns = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combine2(0, target, 0, candidates);
        return this.res;
    }

    public void combine2(int begin, int target, int sum, int[] candidates) {
        if (sum == target) {
            this.res.add(new ArrayList<>(this.oneAns));
            return;
        }
        for (int i = begin; i < candidates.length; ++i) {
            if (candidates[i] > target - sum) {
                break;
            }
            // i > begin是去重精髓，begin表示当前层的起始节点的下标
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            this.oneAns.add(candidates[i]);
            combine2(i + 1, target, sum + candidates[i], candidates);
            this.oneAns.remove(this.oneAns.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,5,2,1,2};
        CombinationSum2 o = new CombinationSum2();
        List<List<Integer>> res = o.combinationSum2(arr, 5);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
