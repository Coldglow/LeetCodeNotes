// https://leetcode.cn/problems/house-robber-ii/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/06/2023 12:40
 * Scanner in = new Scanner(System.in);
 */
public class Rob02 {
    /**
     * 因为nums[0] 和 nums[n - 1]是连着的, 所以可以从两个起点遍历两次
     * 第一次是nums[0] ... nums[n - 2], 第二次是 nums[1] ... nums[n - 1]
     * 返回两次遍历过程中的最大值
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        // 遍历nums[0] ... nums[n - 2]
        dp[1] = nums[0];
        for (int i = 1; i < n - 1; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        int tmp = dp[n - 1];  // 记录第一次遍历的结果
        dp[1] = nums[1];
        // 遍历nums[1] ... nums[n - 1]
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return Math.max(tmp, dp[n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1};
        System.out.println(rob(arr));
    }
}
