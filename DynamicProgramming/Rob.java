// https://leetcode.cn/problems/house-robber/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/06/2023 12:13
 * Scanner in = new Scanner(System.in);
 */
public class Rob {
    /*
        dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }

        return dp[n];
    }
}
