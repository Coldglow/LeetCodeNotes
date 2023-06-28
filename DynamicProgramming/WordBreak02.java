// https://leetcode.cn/problems/word-break-ii/
package DynamicProgramming;

import java.util.*;

/**
 * @author David Wong
 * @date 26/06/2023 10:29
 * Scanner in = new Scanner(System.in);
 */
public class WordBreak02 {

    private List<String> res;
    private List<String> temp;
    private HashSet<String> set;

    public List<String> wordBreak(String s, List<String> wordDict) {
        res = new LinkedList<>();
        set = new HashSet<>();
        set.addAll(wordDict);
        temp = new LinkedList<>();
        breakString(s, temp);
        return res;
    }

    // 如何去重
    public void breakString(String s, List<String> temp) {
        if (Objects.equals(s, "")) {
            StringBuilder sb = new StringBuilder();
            for (String str : temp) {
                sb.append(str);
            }
            sb.deleteCharAt(sb.length() - 1); // 去掉最后的空格
            res.add(sb.toString());
            return;
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            int flag = 0;
            for (int j = 0; j < i && !dp[i]; j++) {
                String word = s.substring(j, i);
                if (set.contains(word) && dp[j]) {
                    dp[i] = true;
                    flag = 1;
                    temp.add(word + " ");
                    breakString(s.substring(i, n), temp);
                    break;
                }
            }
            if (flag == 1) {
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        WordBreak02 obj = new WordBreak02();
        String s = "pineapplepenapple";
        String[] worddict = new String[] {"pine", "apple", "applepen", "pen", "pineapple"};
        List<String> wordDict = List.of(worddict);
        List<String> res = obj.wordBreak(s, wordDict);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
