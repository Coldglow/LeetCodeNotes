// https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 02/03/2023 09:51
 * Scanner in = new Scanner(System.in);
 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int res = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                res = Math.max(res, temp);
                temp = 1;
            } else {
                temp++;
            }
        }
        return Math.max(res, temp);
    }
}
