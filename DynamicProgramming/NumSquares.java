// https://leetcode.cn/problems/perfect-squares/
package DynamicProgramming;

public class NumSquares {
    /*
        转换成背包问题
        1. 因为n的最大值是10000，所以可以选取的物品就是1、4、9、25 ... 10000 这100个数
           因此objects[i] = i * i, i < sqrt(n)，weight也是其本身
           根据n本身的不同，可以选取的物品数也不同，应该是[1 ... sqrt(n) ) 之间的物品
        2. 定义: dp[j] 表示 选取[0 ... i]之间的数，可以组成和为j的这些组合中使用的数最少的组合，所使用的组合数
        3. 状态转移: j < i，dp[j] 不变
                    j >= i， dp[j] = min( dp[j], dp[j - weight[i]] + 1)
        4. 初始化dp[i] = i，因为可使用的物品中一定有1，对于每个数而言，1 * n = n，需要n个1之和才等于n
        5. 遍历顺序，完全背包，组合问题，先物品再重量，都是从左到右
     */
    public int numSquares(int n) {
        int sqrtN = (int)(Math.sqrt(n));
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= sqrtN; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println((int)(Math.sqrt(13)));
    }
}
