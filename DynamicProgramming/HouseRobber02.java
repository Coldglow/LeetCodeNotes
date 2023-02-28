// https://leetcode.cn/problems/house-robber-ii/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 27/02/2023 16:13
 * Scanner in = new Scanner(System.in);
 */
public class HouseRobber02 {
    /*
        和1不同的是，这里的房子连成一个环，所以需要考虑第一个房子和最后一个房子之间的关联
        因此只是初始化的地方不一样
        1. 状态定义：dp【i】 表示当前可能获得的最大金额
        2. 状态转移：依然是max(dp[i - 2] + nums[i]，dp[i - 1])
        3. 初始化，有两种可能：
            a. 第一个房子和最后一个房子之间，选择第一个房子，那么需要遍历的数组部分就是[0 ... n - 2]之间
            b. 第一个房子和最后一个房子之间，选择第最后一个房子，那么需要遍历的就是[1 ... n - 1] 之间
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return n == 1 ? nums[0] : Math.max(nums[0], nums[1]);
        }
        return Math.max(robAction(nums, 0, n - 1), robAction(nums, 1, n));
    }

    public int robAction(int[] arr, int start, int end) {
        int x = 0, y, z = 0;
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + arr[i]);
            x = y;
        }
        return z;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,2};
//        System.out.println(rob(arr));
    }
}
