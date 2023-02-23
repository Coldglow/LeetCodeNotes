// https://leetcode.com/problems/maximize-sum-of-array-after-k-negations/
package Greed;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximizeSumOfArrayAfterKNegations {
    // 错误的
    public static int largestSumAfterKNegations(int[] nums, int k) {
        if (nums.length == 1) {
            return k % 2 == 0 ? nums[0] : -nums[0];
        }
        Arrays.sort(nums);
        int i = 0;
        while (k > 0) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            } else if (nums[i] > nums[i - 1]) {
                nums[i - 1] = -nums[i - 1];
            } else {

            }
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum;
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        // 统计词频
        for (int n : nums) {
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);
        }
        int sum = Arrays.stream(nums).sum();  // 计算数组的和
        for (int i = -100; i < 0; ++i) {
            if (frequency.containsKey(i)) {
                // k和出现次数的较小值
                int ops = Math.min(k, frequency.get(i));
                sum += (-i) * ops * 2;  // 每翻转一个负数，数组和都机上两倍的该负数的相反数
                frequency.put(i, frequency.get(i) - ops); // 已经有ops次被翻转了
                frequency.put(-i, frequency.getOrDefault(-1, 0) + ops);
                k -= ops;  // k减少ops次
                if (k == 0) {
                    break;
                }
            }
        }
        // 如果k > 0，说明必须翻转正数了
        // 如果包含0  则直接返回，因为无论剩多少次，翻转0对结果无影响
        if (k % 2 == 1 && !frequency.containsKey(0)) {
            for (int i = 1; i < 100; ++i) {
                if (frequency.containsKey(i)) {
                    sum -= i * 2;  // 一个正数变成负数，数组和要减去两倍的这个正数
                    break;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-2,9,9,8,4};
        largestSumAfterKNegations(arr, 3);
    }
}
