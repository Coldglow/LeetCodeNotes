// https://leetcode.com/problems/n-queens/
package BackTracking;

import java.util.*;

public class N_Queens {
    private List<List<String>> res = new ArrayList<>();
    private LinkedList<String> temp = new LinkedList<>();
    private int[] record;

    public List<List<String>> solveNQueens(int n) {
        record = new int[n];
        backTracking6(0, 0, n);
        return this.res;
    }

    /**
     *
     * @param row  第index行
     * @param Q  目前有多少个皇后
     * @param n  一共需要摆放几个
     */
    public void backTracking6(int row, int Q, int n) {
        if (row == n) {
            this.res.add(new ArrayList<>(this.temp));
            return;
        }

        char[] oneRow = new char[n];
        Arrays.fill(oneRow, '.');
        for (int col = 0; col < n; ++col) {
            if (!check(row, col, n)) {  // 检查第row行 第col列能否摆放
                continue;
            }
            record[row] = col;  // 表示第row行第col列放了Queen
            oneRow[col] = 'Q';
            this.temp.add(new String(oneRow));
            backTracking6(row + 1, Q + 1, n);
            record[row] = 0;
            oneRow[col] = '.';
            this.temp.pollLast();
        }
    }

    public boolean check(int i, int j, int n) {  // 检查第i行第j列能否摆放
        for (int col = 0; col < i; ++col) {  // 从第0列开始检查
            // 如果共列  或者共斜线（行减去行，列减去列）
            if (record[col] == j || (Math.abs(col - i) == Math.abs(record[col] - j))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 2};
        N_Queens o = new N_Queens();
        List<List<String>> res = o.solveNQueens(4);
        for (List<String> list : res) {
            for (String i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
