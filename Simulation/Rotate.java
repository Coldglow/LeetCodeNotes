// https://leetcode.cn/problems/rotate-image/
package Simulation;

/**
 * @author David Wong
 * @date 21/06/2023 12:04
 * Scanner in = new Scanner(System.in);
 * 找规律
 */
public class Rotate {
    // 有错
    public static void rotate(int[][] matrix) {
        int n = matrix[0].length;
        // 要交换 n / 2层, 每层交换 4 * (n - 1)个数
        // 每向内走一层, n就减2
        for (int circle = 0; circle < n / 2; circle++) {
            int i = circle, j = circle;  // 每一层开始的位置
            int row = n - circle * 2 - 1;   // 这一层每行要遍历几个
            // 这一层一共要交换 4 * (row - 1)个, 分成row - 1批次交换, 每次交换完, i和j都要更新
            for (int batch = 0; batch < row; batch++) {
                int tmp = matrix[j][n - 1 - i];
                // (i, j) 替换 (j, n - 1 - i)位置的数
                matrix[j][row - i] = matrix[i][j];
                // (i, j) 被 (n - 1 - j, i)位置的数替换
                matrix[i][j] = matrix[row - j][i];
                // (n - 1 - j, i)位置的数被 (i + n - 1, j + n - 1)位置的数替换
//                matrix[row - 1 - j][i] = matrix[i + row - 1][j + row - 1];
                matrix[row - j][i] = matrix[n - 1 - i][row - j];
                // (i + n - 1, j + n - 1)位置的数被 (j, n - 1 - i)位置的数替换
                matrix[n - 1 - i][row - j] = tmp;
                j++;  // 列自增, 行不用
            }
        }
    }

    // 直接双循环会导致重复交换, 那么每次循环只交换一半不就行了
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
        rotate(matrix);
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(ints[j] + "  ");
            }
            System.out.println();
        }
    }
}
