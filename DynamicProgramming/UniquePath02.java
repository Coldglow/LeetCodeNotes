// https://leetcode.com/problems/unique-paths-ii/
package DynamicProgramming;

/**
 * 2023.2.22
 */
public class UniquePath02 {
    // 和上一个的状态转移方程一样，只不过在初始化的时候不同
    // 如果障碍物在第一行或者第一列，那么从障碍物所在位置开始，后面的都是0
    // 如果不是在第一行或者第一列，那么在障碍物所在位置当作0处理即可
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        // 初始化
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }

        for (int j = 0; j < m; j++) {
            if (obstacleGrid[j][0] == 1) {
                break;
            }
            dp[j][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[1][2];
//        arr[1][1] = 1;
        uniquePathsWithObstacles(arr);
    }
}
