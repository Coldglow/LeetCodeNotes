package DynamicProgramming;// https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/

public class MaxProfit {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }
        int buy = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            // 更新buy的时候sell也同时更新
            if (prices[i] < buy) {
                buy = prices[i];
            }
            res = Math.max(res, prices[i] - buy);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxProfit obj = new MaxProfit();
        int[] arr = new int[] {7,1,5,3,6,4};
        System.out.println(obj.maxProfit(arr));
    }
}
