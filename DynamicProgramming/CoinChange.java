package DynamicProgramming;// https://leetcode.cn/problems/coin-change/

public class CoinChange {
    /**
     * dp的思路一般都是从末状态一直递推到初始状态
     * 对于这道题来说，如果想知道amount = 6, coins = [3, 1, 2]的合法组合
     * 一种有效的递归方式是:
     * 1. amount - 3, 根据amount == 3的状态得到amount == 6的状态
     *    1) 3 - 3
     *    2) 3 - 1
     *    3) 3 - 2
     * 2. amount - 1, 根据amount == 5的状态得到amount == 6的状态
     * 3. amount - 2，根据amount == 4的状态得到amount == 6的状态
     * 这样一直递推下去，自然而然可以得出，base case是两种情况
     * 1. amount < 0, 此时不是一种合法的组合  返回 -1
     * 2. amount == 0，合法的组合，返回0
     * 将所有返回值加起来即可
     * 刚开始的想法就是错的，想的是根据数组的数来递推，但其实应该根据amount来推
     * 记得dp数组开 N+1
     * @param coins  coins
     * @param amount  amount
     * @return  re
     */
    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        return change1(coins, amount, dp);
    }

    // 以 coins[i]为最大值，返回可能的找零结果中需要硬币的最少的
    public int change1(int[] coins, int amount, int[] dp) {
        if (amount < 0) {   // amount < 0不是合法的组合
            return -1;
        }
        // 是合法的组合，不返回1是因为amount==0的时候只是用来判断这条路径
        // 是否合法，dp值的修改和返回值的大小无关，而和它是否合法有关
        // 所以返回0
        if (amount == 0) {
            return 0;
        }
        // 查询
        //
        if (dp[amount] != 0) {
            return dp[amount];
        }
        // 遍历数组，得到所有可能的amount
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            // num是amount == amount - coins[i]的组合结果
            int num = change1(coins, amount - coins[i], dp);
            // 如果存在有效组合 num >= 0
            // 检查是不是比之前的组合所需要的数量少
            if (num < res && num >= 0) {
                // 状态转移  amount - coins[i]状态转移到amount状态
                // 结果加1  因为从amount到 amount - coins[i]再到amount只有一种可能
                // 就是用一个硬币coins[i]  这里加1加的是数量而不是面额
                res = num + 1;
            }
        }
        // 返回之前更新dp的值
        // 如果res没有被更新过，dp[amount]为-1
        // 表示没有合法的组合
        dp[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        int[] coins = new int[] {1, 5, 2};
        System.out.println(obj.coinChange1(coins, 11));
    }
}
