// https://leetcode.cn/problems/spiral-matrix/
package Array;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int top = 0, bottom = n - 1, left = 0, right = m - 1;
        List<Integer> res = new LinkedList<>();
        int cur = 0, end = n * m;
        while (cur < end) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
                cur++;
            }
            top++;
            if (cur == end) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
                cur++;
            }
            right--;
            if (cur == end) {
                break;
            }
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
                cur++;
            }
            bottom--;
            if (cur == end) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
                cur++;
            }
            left++;
            if (cur == end) {
                break;
            }
        }
        return res;
    }
}
