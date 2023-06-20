// https://leetcode.cn/problems/greatest-sum-divisible-by-three/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 19/06/2023 16:42
 * Scanner in = new Scanner(System.in);
 */
public class MaxSumDivThree {
    /**
     * 是用dp没错, 但是没写出来, 解析:
     * <a href="https://leetcode.cn/problems/greatest-sum-divisible-by-three/solution/liang-chong-suan-fa-tan-xin-dong-tai-gui-tsll/">...</a>
     * 定义: dp[i][j] 表示 [0 ... i- 1]之间已经选取的最大值s, s % 3 = j
     * 初始化: dp[0][0] = 0, dp[0][1] = -INF, dp[0][1] = -INF
     * 状态转移:
     *  1. 如果选不选x, 那么dp[i][j] = dp[i - 1][j]
     *  2. 如果选x, 那么dp[i][j] = dp[i - 1][(j + x) % 3] + nums[i]
     *  假如x = 4, 4 % 3 = 1, 并且此时要4, 即第二种情况, 那么就需要找出一个s, 且s % 3 = 2, 这样才能凑出来3的倍数.
     *  此时(1 + 4) % 3 = 2, 所以转移方程是 (j + x) % 3 + nums[i]
     *  所以综合, dp[i][j] = max(1. 2.)
     *  ------------------------
     *  另外使用滚动数组, 只需要记录该列的前一列就可以了
     *  所以dp数组是一个2 * 3的数组
     * @param nums
     * @return
     */
    public int maxSumDivThree(int[] nums) {
        int[][] dp = new int[2][3];
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 3; j++) {
                dp[(i + 1) % 2][j] = Math.max(dp[i % 2][j], dp[i % 2][(j + nums[i]) % 3] + nums[i]);
            }
        }
        return dp[nums.length % 2][0];
    }
}
