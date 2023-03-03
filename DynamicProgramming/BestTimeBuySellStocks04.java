package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/02/2023 11:51
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocks04 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        // j为奇数的时候是买入，j为偶数的时候是卖出状态
        int[][] dp = new int[n][k * 2 + 1];
        // 初始化 dp[0][j] = -prices[0] 当j为奇数的时候
        for (int j = 1; j < k * 2 + 1; j += 2) {
            dp[0][j] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 2 * k - 1; j += 2) {
                // 奇数的时候是买入
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                // 偶数的时候是卖出
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        return dp[n - 1][2 * k];
    }
}
