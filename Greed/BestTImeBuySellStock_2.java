// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
package Greed;

public class BestTImeBuySellStock_2 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > buy) {
                profit += prices[i] - buy;
            }
            buy = prices[i];
        }
        return profit;
    }
}
