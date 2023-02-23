package Array;// https://leetcode.cn/problems/find-all-duplicates-in-an-array/

import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
    // 数组原地修改，将nums[i]放到对应的位置上
    // 然后再遍历数组，如果nums[i]不等于i-1说明重复出现
    // 时间复杂度 O(N)  空间复杂度 O(1)
    public List<Integer> findDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        // for 循环结束后数组中的值会被放在自己对应的位置
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]- 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
