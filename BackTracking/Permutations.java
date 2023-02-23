package BackTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    private List<List<Integer>> res = new ArrayList<>();
    private LinkedList<Integer> temp = new LinkedList<>();
    private int[] used;  // 排列问题的数组要全局共享

    public List<List<Integer>> permute(int[] nums) {
        this.used = new int[20];
        backTracking4(nums);
        return this.res;
    }

    public void backTracking4(int[] nums) {
        if (this.temp.size() == nums.length) {
            this.res.add(new ArrayList<>(this.temp));
            return;
        }
        for (int num : nums) {
            if (used[num + 10] == 1) {
                continue;
            }
            this.temp.add(num);
            used[num + 10] = 1;
            backTracking4(nums);
            used[num + 10] = 0;
            this.temp.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 3};
        Permutations o = new Permutations();
        List<List<Integer>> res = o.permute(arr);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
