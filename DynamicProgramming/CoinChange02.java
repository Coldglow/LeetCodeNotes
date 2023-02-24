// https://leetcode.cn/problems/coin-change-ii/
package DynamicProgramming;

import java.util.Arrays;
/**
    2023.2.24
 */

public class CoinChange02 {
    /*
        完全背包问题，每个物品可以使用无限次数
        1. dp[j] 表示用[0 ... i]之间的硬币组成金额j的组合个数
        2. 当j < coins[i]：表示硬币额度大于要组合的金额数，所以直接使用金额为i-1时候的组合数
                即dp[j] = dp[j]，表示此时的dp[j]由i-1位置硬币的组合数变化过来
           当j >= coins[i]：则状态应该由dp[j - coins[i]] 变换过来，因为是求组合数，所以相加
                dp[j] += dp[j - coins[i]]
           因此总的状态转移方程就是 dp[j] = j % coins[i] == 0 ? dp[j- 1] + 1 : dp[j - 1]
        3. 初始化dp[0] = 1，因为如果dp[0] = 0，后面都是0
        4. 外层左到右，内层也是左到右，因为是完全背包，每个物品可以使用多次
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化, ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        dp[0] = 1;  // 不要纠结为什么dp[0] = 1，因为如果dp[0] = 0，那么后面的都是0，所以只能是1

        // 先遍历物品，组合问题，硬币的使用顺序不重要
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }


    public static int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化, ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        dp[0] = 1;  // 不要纠结为什么dp[0] = 1，因为如果dp[0] = 0，那么后面的都是0，所以只能是1

        System.out.println("************************");
        // 先遍历容量，排列问题，硬币的使用顺序重要
        for (int j = 0; j <= amount; j ++) {
            for (int coin : coins) {
                if (j - coin >= 0) {
                    dp[j] += dp[j - coin];
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[] {1, 2, 5};
        System.out.println(change(5, coins));
        System.out.println(change2(5, coins));
    }
}
