package DynamicProgramming;

/**
 * @author David Wong
 * @date 01/03/2023 09:27
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocksWithCoolDown {
    /*
        1. dp[i][j] 表示 在第i天处于状态j时候手上剩余的最大现金额
        2. 状态转移：
            b) 买入
                1) 第i天买入,可以从第i-1天是冷冻期或者没有操作两个状态转移而来
                    如果从冷冻期转移而来，那么dp[i][0] = dp[i-1][2] - prices[i]
                    如果从没有操作状态转移来，那么dp[i][0] = 0 - prices[i]
                2) 第i天没有操作，而是沿用了前一天的买入状态
                    那么dp[i][0] = dp[i - 1][0]
                因此 dp[i][0] = max(dp[i-1][2] - prices[i], -prices[i], dp[i-1][0])
            c) 卖出，
                1) 第i天卖出，那么可以从第i-1天的买入状态转移而来
                    dp[i][1] = dp[i - 1][0] + prices[i]
                2) 第i天没操作，沿用第i-1天的卖出状态
                    dp[i][1] = dp[i - 1][1]
                因此，dp[i][1] = max(dp[i - 1][0] + prices[i], dp[i - 1][1])
            d) 冷冻期：不能操作，只能是前一天的卖出状态的金额，dp[i][3] = dp[i - 1][1]
        3. 初始化
           dp[0][0] = -prices[i]
           dp[0][1] = 0
           dp[0][2] = 0
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][2] - prices[i], -prices[i]), dp[i -1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
            dp[i][2] = dp[i - 1][1];
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,0,2};
        System.out.println(maxProfit(arr));
    }
}
