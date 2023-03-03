package DynamicProgramming;

/**
 * @author David Wong
 * @date 01/03/2023 10:14
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocksWithFee {
    /*
        1. dp[i] 表示 第i天手上剩余的金额，在卖出的时候交费
        2. 状态转移，每一天都有可能处于两种状态
            a) 买入状态
                1. 买入，从前一天的卖出状态变换到买入状态
                    dp[i][0] = dp[i - 1][1] - prices[i]
                2. 沿用上一天的买入状态 dp[i] = dp[i - 1][0]
            b) 卖出：
                1. 卖出，从前一天的买入状态变换到卖出状态
                    dp[i][1] = dp[i - 1][0] + prices[i] - fee
                2. 沿用前一天的卖出状态，dp[i][1] = dp[i - 1][1]
        3. 初始化
            dp[0][0] = - prices[0]
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        System.out.println(dp[0][0] + " " + dp[0][1]);
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] - prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i] - fee, dp[i - 1][1]);
            System.out.println(dp[i][0] + " " + dp[i][1]);
        }
        return dp[n - 1][1];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit(arr, 2));
    }
}
