// https://leetcode.cn/problems/search-a-2d-matrix-ii/
package Simulation;

/**
 * @author David Wong
 * @date 22/06/2023 12:19
 * Scanner in = new Scanner(System.in);
 */
public class SearchMatrix {
    /**
     * Z字查找, 从右上角向左下角查询
     * 如果matrix[x][y] == target,说明找到, 返回
     * 如果matrix[x][y] > target, 说明 y 这一列的值都大于 target, y--
     * 如果matrix[x][y] < target, 说明 x 这一行左侧剩下的值都小于target, x++
     * 如果x 或者 y越界, 则说明不存在
     * --------------------
     * 多尝试从不同的起点启动, 不要总是惯性思维把左上角作为起点
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int x = 0, y = col - 1;
        while (x < row && y > -1) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }
}
