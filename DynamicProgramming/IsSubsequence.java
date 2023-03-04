// https://leetcode.cn/problems/is-subsequence/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 04/03/2023 09:41
 * Scanner in = new Scanner(System.in);
 */
public class IsSubsequence {
    /*
        可以用双指针，但是为了后面练编辑距离类型的dp，这里先用dp的思路写
        1. dp数组含义:
           dp[i]][j] 表示 以下标i - 1为结尾的字符串s，和以下标j - 1为结尾的字符串t，相同子序列的长度
        2. 状态转移：
           a) 如果s[i - 1] == t[j - 1]，说明t中找到了一个字符与s匹配，那么dp[i][j] = dp[i- 1][j - 1] + 1
           b) 如果不相等，相当于t需要做一个删除操作，删除j - 1位置的字符，然后继续向前匹配。比较i - 1位置和
              j - 2位置的字符，也即dp[i][j] = dp[i][j - 1]。这里不用再次比较，直接复制，因为在计算dp[i][j - 1]的
              时候已经比较过了，所以直接赋值。
              dp[i][j - 1]表示以下标i - 1为结尾的字符串s，和以下标j - 2为结尾的字符串t，相同子序列的长度
        3. 初始化：
           根据递推公式可以看出dp数组依赖于dp[i - 1][j - 1]和dp[i][j - 1], 因此dp[0][0]和dp[i][0]一定要
           初始化
     */
    public boolean isSubsequence(String s, String t) {
        int ns = s.length(), nt = t.length();
        if (ns > nt){
            return false;
        }
        int[][] dp = new int[ns + 1][nt + 1];
        for (int i = 1; i <= ns; i++) {
            for (int j = 1; j <= nt; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[ns][nt] == ns;
    }

    // 双指针
    public boolean isSubsequence2(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        if (ns > nt) {
            return false;
        }
        int is = 0, it = 0;
        while(is < ns && it < nt) {
            if(s.charAt(is) == t.charAt(it)) {
                is++;
            }
            it++;
        }
        return is == ns;
    }
}
