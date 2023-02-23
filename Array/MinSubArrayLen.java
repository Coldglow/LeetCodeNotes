// https://leetcode.com/problems/minimum-size-subarray-sum/
package Array;

public class MinSubArrayLen {
    // 滑动窗口
    public int minSubArrayLen(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int L= 0, R = 0;
        int subSum = 0;
        int res = nums.length + 1;  // 这里一定要初始化一个大于nums长度的数，避免出现整个数组加起来才是target的情况
        while (R < nums.length) {
            subSum += nums[R];
            while (subSum >= target) {
                res = Math.min(res, R - L + 1);
                subSum -= nums[L++];
            }
            R++;
        }
        return res == nums.length + 1 ? 0 : res;
    }

    // 还有一种解法就是用一个数组sum来记录 nums[0...i-1]范围的前缀和
    // 所以i位置的和就是sum[i] + nums[i]
    // 可以在sum数组中通过二分查找 找到 最大的 j使得sum[j] + nums[i] 大于等于 target
    // 此时子数组的长度就是
}
