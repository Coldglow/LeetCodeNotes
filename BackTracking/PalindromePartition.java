// https://leetcode.com/problems/palindrome-partitioning/
package BackTracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PalindromePartition {

    private List<List<String>> res = new ArrayList<>();
    private Deque<String> stack = new ArrayDeque<>();

    /**
     * 每次选取[0,i]位置的字符串判断是否是回文，如果是则继续向右遍历
     * 如果不是之间返回
     * base case是遍历到末尾：添加走过的路径。
     * @param s s
     * @return sss
     */
    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        dfs(0, chars);
        return this.res;
    }

    public void dfs(int i, char[] chars) {
        if (i == chars.length) {
            this.res.add(new ArrayList<>(this.stack));
            return;
        }
        for (int j = i; j < chars.length; ++j) {
            if (!isPalindrome(chars, i, j)) {
                // 不应该是return  否则回文串长度是奇数的情况下一直返回false
                // 而且这里写continue不会进行下一层的递归
                // 因为下面的代码就不会执行
                continue;
            }
            this.stack.addLast(new String(chars, i, j - i + 1));
            dfs(j + 1, chars);
            this.stack.pollLast();
        }
    }

    public boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "aba";
        PalindromePartition o = new PalindromePartition();
        List<List<String>> res = o.partition(str);
        for (List<String> list : res) {
            for (String i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
