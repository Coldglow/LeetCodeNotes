// https://leetcode.com/problems/min-cost-climbing-stairs/description/
package DynamicProgramming;
/**
 * 2023.2.22
 */
public class MinimumCostsClimbingStairs {
    // 转移方程
    // dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    // 也可以用两个变量记录，不用数组
    public int minCostClimbingStairs2(int[] cost) {
        int last = 0;
        int pre = 0;
        int costs = 0;
        for (int i = 2; i <= cost.length; i++) {
            costs = Math.min(pre+ cost[i - 1], last + cost[i - 2]);
            last = pre;
            pre = costs;

        }
        return costs;
    }
}
