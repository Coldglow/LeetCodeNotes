// https://leetcode.com/problems/subsets-ii/
package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subset2 {
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> oneSub = new LinkedList<>();
    private LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
//        getSub(nums, 0);
        backTracking2(0, nums);
        return this.res;
    }

    public void getSub(int[] arr, int index) {
        this.res.add(new ArrayList<>(this.oneSub));
        for (int i = index; i < arr.length; ++i) {
            if (i > index && arr[i] == arr[i - 1]) {
                continue;
            }
            this.oneSub.add(arr[i]);
            getSub(arr, i + 1);
            this.oneSub.removeLast();
        }
    }

    public void backTracking2(int index, int[] nums) {
        if (index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; ++i) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            this.temp.add(nums[i]);
            this.res.add(new ArrayList<>(this.temp));
            backTracking2(i + 1, nums);
            this.temp.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,2};
        Subset2 o = new Subset2();
        List<List<Integer>> res = o.subsetsWithDup(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
