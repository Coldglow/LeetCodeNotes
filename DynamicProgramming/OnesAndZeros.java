// https://leetcode.com/problems/ones-and-zeroes/
package DynamicProgramming;

public class OnesAndZeros {
    /*
        背包问题的扩展，从一个物品变成了两个物品
        相应的需要考虑的维度也变成了两个，也就是说内层for循环需要写两个，而且是嵌套关系
        置于先遍历谁则无所谓

        状态定义：dp[i][j]表示在最多使用i个1和j个0的情况下的子集的最大数量
        状态转移：
            1. 对于每个字符串，首先遍历得出字符串中0和1的个数zeros和ones。
            如果i < ones或j < zeros，说明这个字符串不能使用。此时dp[i][j]的状态就应该是遍历上一个字符串
            的时候的dp[i][j]，由于是内循环是逆序，所以此时的dp[i][j]就是遍历上一个字符串的时候的dp[i][j]，
            因此在这种情况下 dp[i][j] = dp[i][j]
            前一个是遍历到第k个字符串时候的dp[i][j]，后一个是遍历到第k-1个字符串的时候的dp[i][j]

            2. 如果i >= ones 或 j >= zeros，说明这个字符串可以使用，那么就要比较用这个字符串和不用这个字符串
            结果哪个大。
            如果用，相应的0和1的数量就要增加，从哪里增加，增加多少。从dp[i - ones][j - zeros]处增加，增加1，
            1表示使用这个字符串。
            因此状态应该是 dp[i][j] = max( dp[i][j], dp[i - ones][j - zeros] + 1)

            3. 两个选择结合起来即可

        初始化：因为是逆序遍历，当i和j小于ones或者zeros时，自然就是0

        遍历顺序：01背包问题内循环都要逆序遍历，注意的是什么时候停止遍历即可。在这里就是当能装下的1的数量i小于当前字符串
                的1的数量，或者能装下的0的数量小于当前字符串0的数量就停止遍历。剩下的自然就是0.
     */
    public int findMaxForm(String[] strs, int m, int n) {

        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int ones = 0, zeros = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
