// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/02/2023 10:01
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocks03 {
    /*
        1. dp数组定义：dp[i] 表示在第i天卖出股票能获得的的最大收益
        2. 状态转移：
            if ( prices[i] > minVal) { dp[i] = prices[i] - minVal } else dp[i] = 0
        3. 初始化：dp[0] = prices[0]
     */
    // 需要维护一个从[0 ... i] 的最小值
    // 结果是dp数组中的最大值而不是dp[n - 1]
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        int minPrice = prices[0];
        int[] dp = new int[n];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > minPrice) {
                dp[i] = prices[i] - minPrice;
                res = Math.max(dp[i], res);
            } else {
                dp[i] = dp[i - 1];
                minPrice = prices[i];
            }
        }
        return res;
    }

    // 因为dp数组中的每一个值都只用一次，所以只需要用一个变量来记录即可
    public int maxProfit2(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        int minPrice = prices[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > minPrice) {
                res = Math.max(prices[i] - minPrice, res);
            } else {
                minPrice = prices[i];
            }
        }
        return res;
    }
}
