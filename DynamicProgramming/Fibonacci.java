// https://leetcode.cn/problems/fibonacci-number/
package DynamicProgramming;
/**
 * 2023.2.22
 */
public class Fibonacci {
    // dp[i] = dp[i - 1] + dp[i - 2]
    // dp[0] = 0  dp[1] = 1
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int last = 0, pre = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = last + pre;
            last = pre;
            pre = res;
        }
        return res;
    }
}
