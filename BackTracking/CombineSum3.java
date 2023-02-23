// https://leetcode.com/problems/combination-sum-iii/
package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class CombineSum3 {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        getCombination(k, n, 1, 0);
        return this.res;
    }

    public void getCombination(int k, int n, int cur, int curSum) {
        // 两个if每个每个条件都不能少
        if (curSum == n && this.temp.size() == k) {
            this.res.add(new ArrayList<>(this.temp));
            return;
        }
        if (curSum > n || this.temp.size() > k || cur > 9) {
            return;
        }
        // 要cur
        this.temp.add(cur);
        getCombination(k, n, cur + 1, curSum + cur);
        // 不要cur
        this.temp.remove(this.temp.size() - 1);
        getCombination(k, n, cur + 1, curSum);
    }

    public static void main(String[] args) {
        CombineSum3 o = new CombineSum3();
        List<List<Integer>> res = o.combinationSum3(9, 45);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
