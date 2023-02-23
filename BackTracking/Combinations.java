// https://leetcode.com/problems/combinations/
package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    private List<List<Integer>> res;
    private List<Integer> oneCombination;
    // 从左到右尝试，分成要和不要两种，记得剪枝
    public List<List<Integer>> combine(int n, int k) {
        this.res = new ArrayList<>();
        this.oneCombination = new ArrayList<>();
        count(n, k, 1);
        return this.res;
    }

    public void count(int n, int k, int i) {
        // 剪枝，如果当前列表的长度加上剩余列表的长度小于k，说明数量不够，不用再递归了
        if ((n - i + 1) + oneCombination.size() < k) {
            return;
        }
        // 一个结果，注意这里需要重新new一个列表出来，否则最后的结果全是空
        if (oneCombination.size() == k) {
            this.res.add(new ArrayList<>(oneCombination));
            return;
        }
        // 要i
        oneCombination.add(i);
        count(n, k, i + 1);  // 注意这里不要直接i++，这样会改变i的大小
        // 不要i
        oneCombination.remove(oneCombination.size() - 1);
        count(n, k, i + 1);
    }

    public static void main(String[] args) {
        Combinations o = new Combinations();
        List<List<Integer>> res = o.combine(5, 3);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
