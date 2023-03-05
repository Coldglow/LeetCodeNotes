// https://leetcode.cn/problems/edit-distance/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 05/03/2023 10:11
 * Scanner in = new Scanner(System.in);
 */
public class EditDistance {
    /*
        这道题和 583. 两个字符串的删除操作(DeleteOperationForTwoStrings.java) 一模一样，因为插入和替换操作都能等价于删除操作。
        a) 向word1中插入，相当于删除word2
        b) 替换word1中的一个字符，相当于删除再插入，也即删除word1中要被替换的字符，再删除word2中对应的字符

        1. 状态定义：
           dp[i][j]表示word1中以i - 1为结尾的字符串变成word2中以j - 1为结尾的字符串
        2. 状态转移：
           和 583 一样，不过因为有了替换操作，所以当两个都删除的时候，只需要+1，不需要+2.
     */
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1;j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                int temp = Math.min(dp[i - 1][j], dp[i][j - 1]);
                // 和583的代码完全一样，只有这里变了，改成了 dp[i - 1][j - 1] + 1
                dp[i][j] = dp[i - 1][j - 1] < temp ? dp[i - 1][j - 1] + 1 : temp + 1;
            }
        }
        return dp[n1][n2];
    }
}
