// https://leetcode.cn/problems/4sum/
package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    // 就是在三数之和的基础上套了一层循环
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 排除重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            threeSum(nums, i, res, target);
        }
        return res;
    }

    public void threeSum(int[] nums, int index, List<List<Integer>> res, int target) {
        Arrays.sort(nums);
        int L, R;
        int len = nums.length;
        for (int i = index + 1; i < len; i++) {
            if (i > index + 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            L = i + 1;
            R = len - 1;
            while (L < R) {
                if (nums[i] + nums[L] + nums[R] + nums[index] == target) {
                    List<Integer> oneTri = new ArrayList<>();
                    oneTri.add(nums[i]);
                    oneTri.add(nums[L]);
                    oneTri.add(nums[R]);
                    oneTri.add(nums[index]);
                    res.add(oneTri);
                    // 这两个while是排除 -2 0 0 2 2 这种情况
                    // 这种情况一直忘记
                    // 排除第二个数和第三个数重复的情况在这里排除
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++;
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--;
                    }
                    L++;
                    R--;
                } else if (nums[i] + nums[L] + nums[R] + nums[index]  < target) {
                    L++;
                } else {
                    R--;
                }
            }
        }
    }
}
