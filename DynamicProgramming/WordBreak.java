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
        boolean[] dp = new boolean[n + 1];   // boolean类型数组默认初始化为false
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            // 如果dp[i]已经是true, 那么无需再重新寻找
            for (int j = 0; j < i && !dp[i]; j++) {
                // substring(0, i)是左闭右开区间
                // s可以被分成[0 ... j - 1] 和 [j ... i - 1]两部分需要满足两个条件
                //  1. [0 ... j - 1]这部分包含在字典中, 对应到dp数组就是dp[j]
                //  2. [j ... i - 1]也包含在字典中, 对应的就是set.contains(substring(j, i))
                // 如果两个条件都满足, 则说明 [0 ... i - 1]的部分可以被字典中的单词组成
                // 不一定是被一个单词组成, 可以是多个单词组成
                // 这也是为什么dp[0] 要初始化为true, 否则当找到s能够被字典中的单词表示的第一部分时
                // 如果dp[0] = false, 那么之后永远都找不到答案
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new LinkedList<>();
        wordDict.add("leet");
        wordDict.add("code");
        WordBreak o = new WordBreak();
//        o.wordBreak(s, wordDict);
//        System.out.println("123456".substring(0, 3));
    }
}
