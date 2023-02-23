// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    private List<String> res = new ArrayList<>();
    private String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] digitArr = digits.toCharArray();
        StringBuilder sb = new StringBuilder();
        getMap(0, sb, digitArr);
        return this.res;
    }

    public void getMap(int index, StringBuilder sb, char[] digits) {
        if (index == digits.length) {
            this.res.add(new String(sb));
            return;
        }
        // 得到index所对应的字符串
        int number = digits[index] - '0';
        char[] ch = this.map[number].toCharArray();
        for (int j = 0; j < ch.length; ++j) {
            // 要
            sb.append(ch[j]);
            getMap(index + 1, sb, digits);
            // 不要
            sb.deleteCharAt(sb.length() - 1);
//            getMap(index + 1, sb, digits);
        }
    }

    public static void main(String[] args) {
        LetterCombinations o = new LetterCombinations();
        List<String> res = o.letterCombinations("23");
        for (String str : res) {
            System.out.println(str);
        }
    }
}
