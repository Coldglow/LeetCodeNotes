// https://leetcode.cn/problems/permutations/
package BackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations3 {
    private List<List<Integer>> res;
    private List<Integer> oneAns;
    private Set<Integer> used;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        used = new HashSet<>();
        oneAns = new ArrayList<>();
        process(nums, nums.length);
        return res;
    }

    public void process(int[] arr, int n) {
        if (oneAns.size() == n) {
            res.add(new ArrayList<>(oneAns));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used.contains(arr[i])) {
                continue;
            }
            used.add(arr[i]);
            oneAns.add(arr[i]);
            process(arr, n);
            used.remove(arr[i]);
            oneAns.remove(oneAns.size() - 1);
        }
    }

}
