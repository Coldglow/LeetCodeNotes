package DynamicProgramming;

/**
 * 2023.2.22
 * 2023.2.24修改
 *      如果每次不仅仅能选择一步或者两部，而是在一个数组steps中选择可以跳跃的台阶数，返回跳跃到第n个台阶可能的组合数
 *      如果把题目这样改，就变成了完全背包问题，容量是n，物品是steps
 */

public class ClimbingStairs {
    // dp[i] = dp[i - 1] + dp[i - 2]
    // dp[0] = 1  dp[1] = 2
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int last = 1, pre = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = last + pre;
            last = pre;
            pre = res;
        }
        return res;
    }

    /*
     * 2023.2.24 修改
     * 改成完全背包问题，因为是组合问题，涉及物品使用顺序是，所以先遍历容量再遍历物品
     * dp[j] 表示 从[0 ... i] 之间选取可用的步数，可以到达台阶j的步的组合数
     */
    public int climbStairs2(int[] steps, int n) {
        int[] dp = new int[n + 1];
        // dp[0] = 1
        dp[0] = 1;
        // 从第一阶台阶开始遍历
        for (int j = 1; j <= n; j++) {
            for (int step : steps) {
                if (j - step >= 0) {
                    dp[j] += dp[j - step];
                }
            }
        }
        return dp[n];
    }
}
