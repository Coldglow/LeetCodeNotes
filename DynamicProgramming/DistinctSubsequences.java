// https://leetcode.cn/problems/distinct-subsequences/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 04/03/2023 10:15
 * Scanner in = new Scanner(System.in);
 */
public class DistinctSubsequences {
    /*
        明确一下题意，要求s的子序列中有多少个t，所以只需要对s进行删除操作，不对t做任何操作，只需要判断即可。
        1. dp数组含义：
           dp[i][j] 表示 以i - 1为结尾的s的子序列中 出现 以j - 1为结尾的t的子序列 的次数
        2. 状态转移：
           a) s[i - 1] == t[i - 1]
              此时可以分成用s[i - 1]和不用s[i - 1]，因为s中可能存在重复的，因此有可能不用
              1) 用s[i - 1]: 那么就相当于在i - 2和j - 2后面追加了一个i - 1和j - 1，所以dp[i][j] = dp[i - 1][j - 1]
                 一定注意不用加1，因为这里求得不是s和t中公共子序列的个数。
              2) 不用s[i - 1]: 如果不用，相当于删除i - 1位置的字符，向前匹配，所以dp[i][j] = dp[i - 1][j]
              综合这两种可能，因为大前提已经是s[i - 1] == t[j - 1] 了，所以这两种情况都需要考虑
              因此总的可能就是相加，dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
           b) s[i - 1 != t[j - 1]
              此时就只有一种可可能，不能用s[i - 1]。上面的情况是我可以用，但我选择不用，这种情况是我不能用。
              因此dp[i][j] = dp[i - 1][j]，相当于删除s[i - 1]继续向前匹配
        3. 初始化：
           根据状态转移得出，第一行和第一列需要初始化。
           dp[i][0]表示：以 i - 1为结尾的字符串s中出现空字符串的个数，因此dp[i][0]一定都是1，因为只有一种可能出现空字符串
           就是把[0 ... i - 1]的所有字符都删了
           dp[0][j]表示：空字符串中出现以j - 1为结尾的字符串t的个数。因此dp[0][j]一定为0
           dp[0][0] = 1，表示空字符串中出现空字符串的个数。
        4. 遍历顺序：
           左上到右下
     */
    public int numDistinct(String s, String t) {
        int ns = s.length(), nt = t.length();
        if (ns < nt) {
            return 0;
        }
        int[][] dp = new int[ns + 1][nt + 1];
        // 第一列初始化为1
        for (int i = 0; i <= ns; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= ns; i++) {
            for (int j = 1; j <= nt; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[ns][nt];
    }
}
