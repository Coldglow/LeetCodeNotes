// https://leetcode.cn/problems/generate-parentheses/
package BackTracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author David Wong
 * @date 05/04/2023 12:16
 * Scanner in = new Scanner(System.in);
 */
public class GenerateValidParenthesis {
    private List<String> res;
    private StringBuilder sb;
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        sb = new StringBuilder();
        generate(n, n);
        return res;
    }

    public void generate(int left, int right) {
        if (right == 0) {
            res.add(new String(sb));
            return;
        }

        if (left > 0) {
            sb.append('(');
            generate(left - 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (left < right && right > 0) {
            sb.append(')');
            generate(left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateValidParenthesis obj = new GenerateValidParenthesis();
        List<String> res = obj.generateParenthesis(3);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
