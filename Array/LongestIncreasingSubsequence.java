// https://leetcode.cn/problems/longest-increasing-subsequence/
package Array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * 2023.2.20
 */
public class LongestIncreasingSubsequence {
    // dp[i]表示必须选取i位置的数字
    // 如果i位置数字大于j数字的位置，说明i位置的数字可以放在j位置数字之后
    // 那么dp[j]+1 就是i位置的一个答案，因为i位置现在可能已经有答案了
    // 所以把dp[j] + 1和当前dp[i]比较，取较大值放入i位置
    public int lengthOfLIS(int[] nums) {
        int res = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j <= i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 维护一个结果序列，和结果序列的有效长度len，通过二分找到比当前i位置的值小的第一个数
    // 假如是在k位置，那么把k+1位置更新成当前值，k+1之后的值就不要了
    // 因此更新有效长度len = k + 1
//    public int lengthOfLIS2(int[] nums) {
//        int n = nums.length;
//        int[] res = new int[n];
//        res[0] = nums[0];
//        int len = 0, size = 1;
//        for (int i = 1; i < n; i++) {
//
//        }
//    }
}
