// https://leetcode.cn/problems/word-break/
package DynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 2023.2.24
 */
public class WordBreak {
    /*
        也是完全背包的应用，物品是wordDict之内的字符串，背包就是字符串s，只不过背包内特定位置需要填特定内容而已
        1. dp[i] = true表示 字符串[0 ... i - 1]之间的字符经过分割可以由字典中的字符串拼接而成
        2. 状态推导：
           先假设[0 ... i - 1]之间的字符串分成两部分之后可以由字典中的字符串拼接而成，假设是分成了[0 ... j]和[j + 1 ... i - 1]这两部分
           因为j 一定小于 i，所以在计算i的时候，dp[j]一定确定（完全背包问题，外层和内层都是从左到右遍历），因此假如dp[j] = true，那么
           只需要检查[j + 1 ... i - 1] 这个区间内的字符是否可以由字典内的字符串拼接而成
           所以dp[i] = dp[j] && dict.contains(s.subString(j, i))
           只需要考虑分成两部分而不需要考虑分成多部份，因为从左到右，每一小节都考虑分成两部分，那么整体分成多部份的情况就已经被考虑过了
        3. 初始化：dp[0] = true，因为从第一个字符（表示容量为1的时候）开始遍历，因为j < i，所以此时j一定为0，
           根据转移方程，如果dp[0] = false，那么之后的所有情况都是false，所以要初始化为true
        3. 遍历顺序：完全背包，排序问题，所以先遍历背包再遍历物品，都是从左到右
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        System.out.println(s);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i && !dp[i]; j++) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new LinkedList<>();
        wordDict.add("leet");
        wordDict.add("code");
        WordBreak o = new WordBreak();
        o.wordBreak(s, wordDict);
    }
}
