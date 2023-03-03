package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/02/2023 10:27
 * Scanner in = new Scanner(System.in);
 */
public class BestTimeBuySellStocks {
    // 模拟法，有问题
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int first = 0, second = 0;
        int buyPrice = prices[0], sellPrice = prices[0];
        for (int i = 1; i < n; i++) {
            if (prices[i] > sellPrice) {
                sellPrice = prices[i];
            } else {
                if (sellPrice - buyPrice > first) {
                    second = first;
                    first = sellPrice - buyPrice;
                } else if (sellPrice - buyPrice > second) {
                    second = sellPrice - buyPrice;
                }
                buyPrice = prices[i];
                sellPrice = buyPrice;
            }
        }
        if (sellPrice - buyPrice > first) {
            second = first;
            first = sellPrice - buyPrice;
        } else if (sellPrice - buyPrice > second) {
            second = sellPrice - buyPrice;
        }
        return first + second;
    }

    // 动态规划  关键是状态的定义
    /*
        1.  每一天一共就四个状态
            0) 第一次持有股票
            1) 第一次不持有股票
            2) 第二次持有股票
            3) 第二次不持有股票
            dp[i][j] 表示 第 i 天 处于状态j 时所剩最大现金
        2. 状态转移：
            对于状态0：
                a) 第i天买入股票了，那么dp[i][0] = 0 - prices[i]，因为是第一次买入股票，再买入之前的现金
                   为0，因此是 0 - prices[i]
                b) 第i天没有操作，而是沿用前一天的买入状态，即dp[i][0] = dp[i - 1][0]
                因此 dp[i][0] = max(0 - prices[i], dp[i - 1][0])
            对于状态1：
                a) 第i天卖出股票了，手中的现金数量为需要增加，因此dp[i][1] = dp[i - 1][0] + prices[i]
                b) 第i天没有操作，而是沿用前一天的卖出状态，即dp[i][1] = dp[i - 1][1]
                因此 dp[i][1] = max(dp[i - 1][0] + prices[i], dp[i - 1][1])
            状态2和状态3同理：
                dp[i][2] = max(dp[i - 1][2], dp[i - 1][1] - prices[i])
                dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] + prices[i])
        3. 初始化
            第0天做买入的操作，那么手上剩余的现金肯定是 dp[0][0] = -prices[0]
            第0天做卖出的操作，那么肯定是先买入再卖出，所以dp[[0][1] = 0
            第0天做第二次买入的操作，那么肯定是先买入再卖出，再买入 dp[0][2] = -prices[0]
            第0天做第二次卖出的操作，那么肯定是先买入再卖出，再买入再卖出，所以dp[[0][2] = 0
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        if (n == 1) {
            return 0;
        }
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[n - 1][3];
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,2,5,7,2,4,9,0};
        System.out.println(maxProfit(arr));
    }
}
