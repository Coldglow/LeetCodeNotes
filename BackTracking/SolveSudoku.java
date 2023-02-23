// https://leetcode.com/problems/sudoku-solver/
package BackTracking;

public class SolveSudoku {
    // 如果回溯中使用了for循环，那么只有在需要记录结果的时候才需要终止条件
    // 否则for循环会自动退出
    public void solveSudoku(char[][] board) {
        backTracking7(board);
    }

    // 第i行 第j列
    public boolean backTracking7(char[][] board) {
        // 第i行
        for (int i = 0; i < board.length; ++i) {
            // 第j列
            for (int j = 0; j < board.length; ++j) {
                // 如果board[i][j]已经是数字，跳过
                if (board[i][j] != '.') {
                    continue;
                }
                // 查看第i行第j列能否放置数字n
                for (char n = '1'; n <= '9'; ++n) {
                    if (!check(i, j, n, board)) {
                        continue;
                    }
                    board[i][j] = n;
                    // 下一列
                    if (backTracking7(board)) {
                        return true;
                    }
                    board[i][j] = '.';
                }
                // 如果九个数都尝试过了仍然不能满足，那么直接返回false
                // 说明该组数据不能构成一个有效的结果
                return false;
            }
        }
        return true;
    }

    // 检查第i行第j列能否放数字n
    public boolean check(int i, int j, char n, char[][] board) {
        // 检查第i行是否存在数字n
        // 检查第j列是否存在数字n
        for (int k = 0; k < board.length; ++k) {
            if (board[i][k] == n || board[k][j] == n) {
                return false;
            }
        }
        // 检查所属的子方块中是否存在数字n
        int row = getStart(i);
        int col = getStart(j);
        for (int k = 0; k < 3; ++k) {
            for (int t = 0; t < 3; ++t) {
                if (board[k + row][t + col] == n) {
                    return false;
                }
            }
        }
        return true;
    }

    // 得到该位置从哪个位置开始判断
    public int getStart(int i) {
        if (i < 3) {
            return 0;
        } else if (i < 6) {
            return 3;
        } else
            return 6;
    }

    public static void main(String[] args) {
//        String[][] board = new String[9][9];
//        String[] c1 = new String[] {'5','3','.','.','7','.','.','.','.'};
//        String[] c2 = new String[] {'6','.','.','1','9','5','.','.','.'};
//        String[] c3 = new String[] {'.','9','8','.','.','.','.','6','.'};
//        String[] c4 = new String[] {'8','.','.','.','6','.','.','.','3'};
//        String[] c5 = new String[] {'4','.',".","8",".","3",".",".","1"};
//        String[] c6 = new String[] {"7",".",".",".","2",".",".",".","6"};
//        String[] c7 = new String[] {".","6",".",".",".",".","2","8","."};
//        String[] c8 = new String[] {".",".",".","4","1","9",".",".","5"};
//        String[] c9 = new String[] {".",".",".",".","8",".",".","7","9"};
//        board[0] = c1;
//        board[1] = c2;
//        board[2] = c3;
//        board[3] = c4;
//        board[4] = c5;
//        board[5] = c6;
//        board[6] = c7;
//        board[7] = c8;
//        board[8] = c9;
    }
}
