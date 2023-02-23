// 0-1 背包问题
// https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html#_01-%E8%83%8C%E5%8C%85
package DynamicProgramming;

public class Knapsack01 {
    // dp[i][j] 表示 从下标为[0，i]的物品中选取物品，放进容量为j的背包中最大价值
    public int maxValue(int[] weight, int[] value, int n) {
        // 因此dp数组的行就应该是物品的总数量
        // 列应该是可以选取的物品的数量 + 1
        int m = weight.length;
        int[][] dp = new int[m][n + 1];
        // 初始化，dp[0][0] = 0
        // 当只有一个物品时，无论可以选取几个，最大价值都是这个物品的价值，value[0]
        for (int i = 1; i < n; i++) {
            dp[0][i] = value[0];
        }
        // 对于第一列来说，因为j = 0，表示容量为0背包的最大值，因此第一列初始化为0，不用管
        // 状态转移
        // 对于weight[i]，存在两个可能
        // 1. 选择weight[i]，那么dp[i][j]的状态就应该从dp[i - 1][j - weight[i]]转移过来，此时的价值应该是dp[i - 1][j - 1] + value[i]
        // 2. 不选择weight[i]，那么dp[i][j]的状态就应该等于dp[i - 1][j]
        // 因此因为求最大价值，所以二者取较大值
        // 所以状态转移方程就应该是 dp[i][j] = max( dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i] )

        // 开始遍历
        for (int i = 1; i < m; i++) {    // 先遍历物品
            for (int j = 0; j < n + 1; j++) {   // 再遍历背包容量
                if (j < weight[i]) {    // 如果容量不够，那么最大价值就是选取[0 .. i -1]时候的最大价值
                    dp[i][j] = dp[i - 1][j];
                } else {   // 容量足够
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        return dp[m - 1][n];
    }

    // https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-2.html#%E4%B8%80%E7%BB%B4dp%E6%95%B0%E7%BB%84-%E6%BB%9A%E5%8A%A8%E6%95%B0%E7%BB%84
    /**
     * 一维dp数组，本质上就是用i层的数据分别替换掉i-1层数据同时能够保证状态转移成功
     * 同时减少了空间使用
     * @param weight 12
     * @param value 12
     * @param n 123
     * @return  111
     */
    public int maxValue2(int[] weight, int[] value, int n) {
        // dp[j] 表示容量为j的背包所能取得的最大价值
        // 和上面的转移方程相同
        // dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
        // 依然是先遍历物品再遍历背包容量，不过容量要从大到小遍历
        int[] dp = new int[n + 1];
        for (int i = 0; i < weight.length; i++) {
            for (int j = n; j > 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[n];
    }
}
