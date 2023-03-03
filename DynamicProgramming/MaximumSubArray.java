// https://leetcode.com/problems/maximum-subarray/
package DynamicProgramming;

import SlideWindows.MinWindow;

public class MaximumSubArray {
    /**
     * 经典动态规划，状态的定义一定要准确和正确！！！！
     * dp[ i ]表示的是 以nums[i]结尾的数组的子数组和的最大值，并且nums[i]一定是子数组的部分
     * 并且nums[i]一定是子数组的部分
     * 并且nums[i]一定是子数组的部分
     * 并且nums[i]一定是子数组的部分
     * 就是说无论用不用 i 之前的部分， nums[i]一定是这个子数组的结尾
     * 所以一定会使用nums[i]
     * 刚开始状态定义错误，考虑了 可以不以nums[i]作为结尾  的可能，所以转移过程一直写不对
     * dp[i] = max(dp[i - 1] + nums[i], nums[i])
     * @param nums  nums
     * @return re
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 两种情况，都要使用nums[i]
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (res < dp[i]) {
                res = dp[i];
            }
        }
        return res;
    }

    /**
     * 因为问题对于所有状态都是只经过一遍，所以可以用一个变量来代替dp数组
     * @param nums  dd
     * @return ddd
     */
    public int msxSubArray1(int[] nums) {
        int res = Integer.MIN_VALUE;
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], nums[i] + cur);
            res = Math.max(cur, res);
        }
        return res;
    }

    public int maxSubArray3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sum = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            sum = Math.max(sum + nums[i], nums[i]);
            res = Math.max(sum, res);
        }
        return res;
    }

    // 2023.2.18
    public int maxSubArray_4(int[] nums) {
        int sum = Integer.MIN_VALUE, res = Integer.MIN_VALUE;
        for (int n : nums) {
            if (sum < 0 && n > sum) {
                sum = n;
            } else {
                sum += n;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /*
        2023.03.03
     */
    public int maxSubArray_5(int[] nums) {
        int sum = nums[0];
        int res = sum;
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            res = Math.max(res, sum);
        }
        return res;
    }
}
