package Array;// https://leetcode.cn/problems/two-sum/

import java.util.HashMap;

public class TwoSum {
    // 哈希表，看已经记录的key中存在不存在target，存在就返回，不存在就进
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {};
    }
}
