// https://leetcode.cn/problems/combination-sum-iv/
package DynamicProgramming;

/**
 * 2023.2.24
 */

public class Combination04 {
    // 完全背包问题
    // 并且求排列数，因此物品选取顺序重要，所以先遍历背包容量
    // dp[j] += dp[j - nums[i]]
    // 初始化dp[0] = 1
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 0; j <= target; j++) {
            for (int i : nums) {
                if (j - i >= 0) {
                    dp[j] += dp[j - i];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] arr= new int[] {1,2,3};
        System.out.println(combinationSum4(arr, 4));
    }
}
