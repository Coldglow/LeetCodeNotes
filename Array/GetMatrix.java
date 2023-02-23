// https://leetcode.cn/problems/spiral-matrix-ii/
package Array;

import java.util.Arrays;

public class GetMatrix {
    public int[][] getMatrix(int n) {
        int[][] res = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n -1;
        int target = n * n, cur = 1;
        while (cur <= target) {
            // 以下四个循环都要写等于  不然最后一个奇数无法填充进去
            for (int i = l; i <= r; i++) {
                System.out.println("t:" + t + "  i:" + i);
                res[t][i] = cur++;
            }
            t++;
            for (int j = t; j <= b; j++) {
                res[j][r] = cur++;
            }
            r--;
            for (int k = r; k >= l; k--) {
                res[b][k] = cur++;
            }
            b--;
            for (int z = b; z >= t; z--) {
                res[z][l] = cur++;
            }
            l++;
        }
        return res;
    }

    public static void main(String[] args) {
        GetMatrix obj = new GetMatrix();
        int[][] res = obj.getMatrix(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
