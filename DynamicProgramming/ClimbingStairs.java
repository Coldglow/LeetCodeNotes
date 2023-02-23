package DynamicProgramming;

/**
 * 2023.2.22
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
}
