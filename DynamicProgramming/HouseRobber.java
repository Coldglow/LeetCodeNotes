// https://leetcode.cn/problems/house-robber/
package DynamicProgramming;

import java.util.Arrays;

/**
 * @author David Wong
 * @date 27/02/2023 15:38
 */
public class HouseRobber {
    /*
        1. dp[i] 表示 [0 ... i] 之间能取得的最大值
        2. 转移方程：对于每一个房间，都存在取和不取两种可能
            1) 取：那么i位置的值就是dp[i - 1] + nums[i]
            2)不取：那么i位置的值就是dp[i - 1]
        3. 初始化：初始化dp[0] == nums[0]  因为0位置只能取这么一个数
                  dp[1] = max(dp[0[, dp[1])  二者较大值
        4. 遍历顺序：从左到右
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {1,3,1};
        rob(arr);
    }
}