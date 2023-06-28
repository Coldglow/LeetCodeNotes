// https://leetcode.cn/problems/max-area-of-island/
package BackTracking;

/**
 * @author David Wong
 * @date 25/06/2023 11:55
 * Scanner in = new Scanner(System.in);
 */
public class MaxAreaOfIsland {
    /*
        污染法, 从左上角到右下角遍历数组, 每次到一个单元格就计算对应的面积
        如果越界就返回0
        如果不是1也返回0
        如果是1就递归调用上下左右单元格的面积相加再加上该单元格的1返回
     */
    private int maxArea;
    public int maxAreaOfIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int area = getArea(grid, i, j);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }

    public int getArea(int[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length) {
            return 0;
        }

        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return 1 + getArea(grid, i, j + 1) +
                getArea(grid, i, j - 1) +
                getArea(grid, i + 1, j) +
                getArea(grid, i - 1, j);
    }
}
