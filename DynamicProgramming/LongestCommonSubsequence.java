// https://leetcode.cn/problems/longest-common-subsequence/
package DynamicProgramming;

public class LongestCommonSubsequence {
    // 这个方法没改对
    // 下面的do方法是对的
    public int lcs(String text1, String text2) {
        int res = 0;
        for (int i = 0; i < text1.length(); i++) {
            res = Math.max(res, process(text1, text2, i));
        }
        return res;
    }

    public int process(String text1, String text2, int k) {
        int index = -1;  // 返回字符在text2种的下标，不在返回-1
        int res = 0;
        int lastIndex = -1;  // 计入长度的子字符串的最后一个字符的下标

        for (int i = k; i <text1.length(); i++) {
            // 返回字符text1[i]在text2中的下标
            index = check(text2, text1.charAt(i), lastIndex);
            // 如果在，判断是位于上一个有效字符的后面
            // 如果在后面，res++，更新lastIndex
            // 否则什么也不做
            if (index > lastIndex) {
                lastIndex = index;
                res++;
            }
        }
        return res;
    }

    /**
     * 从++index位置开始检查字符c是否出现在text中
     * 没有就返回-1
     * @param text  d
     * @param c d
     * @param index d
     * @return d
     */
    public int check(String text, char c, int index) {
        int lastIndex = index;
        while (++lastIndex < text.length()) {
            if (text.charAt(lastIndex) == c) {
                return lastIndex;
            }
        }
        return index;
    }

    // 要不每次都上个小例子直接看转移方程能不能写出来？
    public int lcsDp(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        System.out.println("-------------");
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[len1][len2];
    }

    /*
        2023.03.03  再次尝试写 直接用dp的思路
        1. 数组定义：dp[i][j] 表示text1中 [0 ... i - 1] 和 text2中 [0 ... j - 1]之间最长公共序列的长度
        2. 状态转移：
            dp[i][j] = nums1[i - 1] == nums2[j - 1] ? max(dp[i][j-1], dp[i-1][j]) + 1 : max(dp[i][j-1], dp[i-1][j])
        3. 初始化，dp[0][0] = 0
        4. 左上角开始到右下角，结果为右下角的值
     */
    public int longestCommonSubsequenceDP(String text1, String text2) {
        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();
        int n1 = ch1.length;
        int n2 = ch2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                dp[i][j] = ch1[i - 1] == ch2[j - 1] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println("-------------");
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[n1][n2];
    }


    public static void main(String[] args) {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        System.out.println(obj.longestCommonSubsequenceDP("abbbb", "babab"));
    }
}
