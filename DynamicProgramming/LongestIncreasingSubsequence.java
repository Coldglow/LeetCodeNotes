// https://leetcode.cn/problems/longest-increasing-subsequence/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 01/03/2023 11:06
 * Scanner in = new Scanner(System.in);
 */
public class LongestIncreasingSubsequence {
    /*
        1. dp[i] 表示 以i结尾的递增子序列的长度
        2. 状态转移：
           dp[i] 等于从[0 ... i - 1]位置符合条件的dp[i] + 1，dp[i]取遍历过程中的最大值
        3. 初始化，不仅仅是把dp[0]初始化为1，要把所有都初始化为1
           因为有两层遍历，所以可以在遍历的时候再复制，省去了单独遍历的时间
        4. 遍历，两层遍历，外层遍历扩充i，内层遍历更新i，都是从左到右
        Note: 结果不是dp[n - 1]   而是遍历过程中的最大值
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int res = 1;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
