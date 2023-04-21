// https://leetcode.com/problems/subsets/

package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> set = new LinkedList<>();
    private LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
//        getSubsets(nums, 0);
        backTracking(0, nums);
        return this.res;
    }

    // 不要被模板困住，该丢就丢，该加就加
    public void getSubsets(int[] nums, int index) {
        this.res.add(new ArrayList<>(this.set));
        for (int i = index; i < nums.length; ++i) {
            this.set.add(nums[i]);
            getSubsets(nums, i + 1);
            this.set.removeLast();
        }
    }

    // 时间复杂度 O(n×2^n)  一共2^n个状态，每个状态需要O(n)的时间来构造子集
    public List<List<Integer>> subsets2(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {   // O(2^n)
            this.set.clear();
            for (int i = 0; i < n; ++i) {    // O(n)
                if ((mask & (1 << i)) != 0) {    // 如果当前序列匹配上  添加到子集中
                    this.set.add(nums[i]);
                }
            }
            this.res.add(new ArrayList<>(this.set));
        }
        return this.res;
    }

    public void backTracking(int index, int[] nums) {
        if (index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; ++i) {
            this.temp.add(nums[i]);
            this.res.add(new ArrayList<>(this.temp));
            backTracking(i + 1, nums);
            this.temp.pollLast();
        }
    }

/* 2023-04-21

    private List<List<Integer>> res;
    private List<Integer> subSet;

    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        res.add(new LinkedList<>());
        subSet = new LinkedList<>();
        backTracking(nums, 0);
        return res;
    }

    public void backTracking(int[] arr, int index) {
        for (int i = index; i < arr.length; i++) {
            subSet.add(arr[i]);
            res.add(new LinkedList<>(subSet));
            backTracking(arr, i + 1);
            subSet.remove(subSet.size() - 1);
        }
    }
 */

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        Subsets o = new Subsets();
        List<List<Integer>> res = o.subsets(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
