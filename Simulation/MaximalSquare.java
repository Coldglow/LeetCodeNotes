// https://leetcode.cn/problems/maximal-square/
package Simulation;

/**
 * @author David Wong
 * @date 21/06/2023 14:43
 * Scanner in = new Scanner(System.in);
 */
public class MaximalSquare {
    // 能过, 但是很慢, 需要优化
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int area = 1;  // 默认为1
                int _x = i + 1, _y = j + 1;
                // 一直向右下角扩张, 直到碰到0或者边界
                while (_x < row && _y < col && matrix[_x][_y] == '1') {
                    if (check(matrix, i, j, _x, _y)) {
                        _x += 1;
                        _y += 1;
                        area++;
                    } else {
                        break;
                    }
                }
                res = Math.max(res, area);
            }
        }
        return res * res;
    }

    public boolean check(char[][] matrix, int x, int y, int i, int j) {
        // 检查列
        while (x < i) {
            if (matrix[x][j] == '0') {
                return false;
            }
            x++;
        }
        // 检查行
        while (y < j) {
            if (matrix[i][y] == '0') {
                return false;
            }
            y++;
        }
        return true;
    }

    /**
     * 动态规划
     * 1. dp[i][j]表示以(i, j)为右下角的最大正方形的边长
     * 2. 数组初始化为0
     * 3. 状态转移:
     *    1. 如果matrix[i][j] == 0, 那么dp[i][j] = 0
     *    2. 如果matrix[i][j] == 1, 那么dp[i][j] = min(左边, 右边, 左上) + 1, 不用考虑最小值是否是0的情况了,
     *    因为即使最小值是0, 加上1之后还是1, 两者情况合并到一起了
     * @param matrix
     * @return
     */
    public int maximalSquareDP(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        int edge = 0;
        // 初始化
        for (int i = 0; i < col; i++) {
            dp[0][i] = matrix[0][i] - '0';
            edge = Math.max(edge, dp[0][i]);
        }
        for (int j = 0; j < row; j++) {
            dp[j][0] = matrix[j][0] - '0';
            edge = Math.max(edge, dp[j][0]);
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    int tmp = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    dp[i][j] = tmp + 1;
                }
                edge = Math.max(edge, dp[i][j]);
            }
        }
        return edge * edge;
    }

    public static void main(String[] args) {
        // ,{'1','0','0','1','0'}
        char[][] matrix = new char[][] {{'0','0','0','1'}, {'1','1','0','1'},{'1','1','1','1'},{'0', '1', '1','1'}, {'0', '1', '1','1'}};
        MaximalSquare maximalSquare = new MaximalSquare();
        int res = maximalSquare.maximalSquare(matrix);
        System.out.println(res);
    }
}
