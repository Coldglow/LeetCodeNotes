package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// https://leetcode.cn/problems/3sum/
public class ThreeSum {
    /**
     * 关键在怎么去重
     * 三个地方都要去重
     * 1. 如果第一个数nums[i]和上一个第一个数nums[i - 1]相等，那么直接跳过
     * 2. 如果第二个数nums[L]和下一个第二个数nums[L + 1]相等，也跳过
     * 3. 如果第三个数nums[R]和下一个第三个数nums[R - 1]相等，也跳过
     * @param nums nums
     * @return list
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int L, R;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 如果排好序之后第一个数大于0，直接返回，不可能存在合法的三元组
            if (nums[i] > 0) {
                return res;
            }
            // 排除重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            L = i + 1;
            R = len - 1;
            while (L < R) {
                if (nums[i] + nums[L] + nums[R] == 0) {
                    List<Integer> oneTri = new ArrayList<>();
                    oneTri.add(nums[i]);
                    oneTri.add(nums[L]);
                    oneTri.add(nums[R]);
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
                } else if (nums[i] + nums[L] + nums[R] < 0) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return res;
    }

    // 2023.2.18  没写出来  思路有点问题
    // 正确思路是 left和left+1比较 right和right-1比较
    // 而不应该是 left和left-1比较 right和right+1比较
    // 因为无论重复不重复，left都要++  right都要--
    // 区别在于重复的时候left要多++一次  或者 right要多--一次
    private List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {
        res = new ArrayList<>();
        // 先排序
        Arrays.sort(nums);
        int n = nums.length;
        // 固定i 然后在[i + 1, n - 1]的范围内寻找
        for (int i = 0; i < n; i++) {
            if (nums[0] > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> ans = new ArrayList<>();
                    Collections.addAll(ans, nums[i], nums[left], nums[right]);
                    res.add(ans);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] arr= new int[] {-1,0,1,2,-1,-4};
        List<List<Integer>> res = obj.threeSum2(arr);
        for (List<Integer> list : res) {
            System.out.println(list.toString());
        }
    }
}
