// https://leetcode.com/problems/unique-paths/
package DynamicProgramming;

import java.util.Arrays;
/**
 * 2023.2.22
 */
public class UniquePath {
    // dp[i][j]表示当 m = i + 1   n = j + 1的时候
    // 从起点到i j位置可能的路径数
    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int j = 0; j < m; j++) {
            dp[j][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
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
        uniquePaths(3, 7);
    }
}
