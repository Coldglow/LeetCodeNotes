// https://leetcode.cn/problems/maximum-product-subarray/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 28/06/2023 13:43
 * Scanner in = new Scanner(System.in);
 */
public class MaxProductSubArray {
    /**
     *  因为数组中存在负, 且要求积, 所以不能简单的认为状态转移方程是 fmax(i) = max(nums[i], nums[i] * fmax(i - 1))
     *  此时需要记录i - 1的两个状态, 一个是最大值的状态, 一个是最小值的状态
     *  当到i的时候, 更新这两个状态:
     *  最大值状态就是 max(nums[i], i-1的最大值 * nums[i], i-1的最小值 * nums[i])
     *  最小值状态就是 min(nums[i], i-1的最大值 * nums[i], i-1的最小值 * nums[i])
     *  都是三者中取最大值和最小值
     *  如果最大值是nums[i],说明nums[i]是一段新的子数组的开始, 如果结果是nums[i] * i - 1的相关状态,
     *  说明nums[i]是在i-1的基础上获得的结果
     *  整个计算过程的最大值就是结果
     *
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxP = nums[0], maxN = nums[0], res = nums[0];
        for (int i = 1; i < n; i++) {
            int mx = maxP, mn = maxN;
            maxP = Math.max(nums[i] * mx, Math.max(mn * nums[i], nums[i]));
            maxN = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            res = Math.max(maxP, res);
        }
        return res;
    }
}
