// https://leetcode.cn/problems/delete-operation-for-two-strings/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 05/03/2023 08:50
 * Scanner in = new Scanner(System.in);
 */
public class DeleteOperationForTwoStrings {
    /*
        1. 状态定义：dp[i][j] 表示以i - 1为结尾的word1的子序列和以j - 1为结尾的word2的子序列相同的最小步数
        2. 状态转移：
           a) word1[i - 1] == word2[j - 1]:
              此时相当于把word1中i - 1位置的字符和word2中j - 1位置的字符都删除，那么就看word1中i - 2和
              word2中j - 2位置的字符，也即dp[i - 1][j - 1]，并且由于动态规划的性质，dp[i - 1][j - 1]已经计算
              过了，因此dp[i][j] = dp[i - 1][j - 1] 因为相等，所以不用做删除操作。
           b) word1[i - 1] != word2[j - 1]:
              此时有三种可能：
              1) 删除word1中i - 1位置的字符
              2) 删除word2中j - 1位置的字符
              3) i - 1和j - 1位置的字符都删除
              按照题目要求，选择操作数最小的那个，于是取
              temp = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) 三者中的最小值
              如果只删除一个，也即前两种可能，那么dp[i][j] = temp + 1, 因为只做了一次删除操作
              如果两个都删除，也即第三种可能，那么dp[i][j] = temp + 2，因为做了两次删除操作
        3. 初始化：
           第一行dp[0][j]表示word1为空字符串，word2以下标j - 1为结尾，做多少次删除操作变成空字符串，肯定是把word2
           中的字符都删除，因此dp[0][j] = j
           第一列dp[i][0]表示word2为空字符串，word1以下标i - 1为结尾，做多少次删除操作变成空字符串，肯定是把word1
           中的字符都删除，因此dp[i][0] = i
        4. 遍历顺序：
           左上到右下
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
                dp[i][j] = dp[i - 1][j - 1] < temp ? dp[i - 1][j - 1] + 2 : temp + 1;
            }
        }
        return dp[n1][n2];
    }
}
