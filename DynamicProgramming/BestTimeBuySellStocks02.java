package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/02/2023 10:16
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocks02 {
    /*
        1. 状态定义：dp[i] 表示在[0 ... i]天能获得的最大利润
        2. 状态转移：if (prices[i] > buyPrice)
                        dp[i] = (prices[i] - buyPrice) + dp[i - 1]
                        buyPrice = prices[i]
                   else
                        buyPrice = prices[i]
                        dp[i] = dp[i - 1]
                   原则就是每一个上升的趋势都累计
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buyPrice = prices[0];
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = prices[i] > buyPrice ? dp[i - 1] + prices[i] - buyPrice : dp[i - 1];
            buyPrice = prices[i];
        }
        return dp[n - 1];
    }
}
