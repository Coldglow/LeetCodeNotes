package DynamicProgramming;// https://leetcode.cn/problems/coin-change/

import java.util.Arrays;

public class CoinChange {

    /*
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

    /**
     * 2023.2.24
     * 使用完全背包的思路写这个题
     * 1. dp[j] 表示 使用 [0 ... i] 之间的硬币可以组成金额j的最小组合数
     * 2. 若 j < coins[i]，说明硬币额度太大，无法构成，于是dp[j] = dp[j]
     *    若 j >= coins[i]，则dp[j]的状态应该从dp[j - coin[i]] 转换过来，即dp[j - coins[i]] + 1
     *    意思是金额为j - coins[i]的时候所需的最少组合数是dp[j - coins[i]]，那么金额j的最少组合数就一定是
     *    往这个组合中添加一个硬币coins[i]，组合数随之加1，此时一定是组成金额j所需的最少硬币个数
     *    总结：dp[j] = min( dp[j], dp[j - coins[i]] + 1)
     * 3. 初始化为dp[0] = 0(系统判定)，其余初始化为一个系统最大值，表示暂时无法构成一个有效组合
     * 4. 求组合数，物品使用顺序无所谓，所以先遍历物品
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化全局最大值
        // 不把0页初始化最大值的原因是，系统判定当金额为0的时候返回0而不是-1
        for (int i = 1; i <= amount; i++) {
            dp[i] = 10001;
        }
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
//                if (j % coin == 0) {
//                    dp[j] = Math.min(j / coin, dp[j - coin] + 1);
//                } else {
//                    dp[j] = dp[j - coin] == 0 ? 0 : dp[j - coin] + 1;
//                }
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount] == 10001 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        int[] coins = new int[] {2, 5, 10, 1};
        System.out.println(obj.coinChange2(coins, 27));
    }
}
