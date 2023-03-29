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


    /*
        2023.03.27
        插入和替换操作都可以转换成删除操作，在状态转移的时候
        既可以是word1变成word2，也可以是word2变成word1，哪个操作次数少用哪个
        1. dp[i][j]表示将word1[0 ... i - 1]和word2[0 ... j - 1]之间的字符转换成相同字符需要的最少操作数
        2. 状态转移: 如果用dp[i][j - 1]表示将word1转换成word2，如果用dp[i - 1][j]则表示将word2转换成word1
                    取二者较小值
        3. 初始化: dp[0][j]表示word1为空字符时，当二者相等时所需要的最少操作次数
                  dp[i][0]同理
     */
    public int minDistance_2(String word1, String word2) {
        char[] ch1 = word1.toCharArray();
        char[] ch2 = word2.toCharArray();
        int[][] dp = new int[ch1.length + 1][ch2.length + 1];

        for (int j = 0; j <= ch2.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 0; i <= ch1.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= ch1.length; i++) {
            for (int j = 1; j <= ch2.length; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                // 如果不相等，则是取三者最小值
                // 如果最小值是dp[i][j - 1],说明将word1变成word2要做的操作次数最少
                // 如果最小值是dp[i - 1][j],说明将word2变成word1要做的操作次数最少
                // 如果最小值是dp[i - 1][j - 1],说明二者要做的操作次数一样
                // 并且这里是+1，因为有替换操作，只需要进行一次，不需要先删除再添加
                int temp = Math.min(dp[i][j - 1], dp[i - 1][j]);
                dp[i][j] = dp[i - 1][j - 1] < temp ? dp[i - 1][j - 1] + 1 : temp + 1;
            }
         }
        return dp[ch1.length][ch2.length];
    }

}
