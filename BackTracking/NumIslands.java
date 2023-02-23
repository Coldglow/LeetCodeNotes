// https://leetcode.cn/problems/number-of-islands/
package BackTracking;

public class NumIslands {
    // 计算岛屿的数量
    // 递归
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    detect(grid, i, j);
                }
            }
        }
        return res;
    }

    public void detect(char[][] grid, int i, int j) {
        // base case 下标越界或者探测到0
        if (i > grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        detect(grid, i - 1, j);
        detect(grid, i + 1, j);
        detect(grid, i, j -1);
        detect(grid, i, j + 1);
    }
}
