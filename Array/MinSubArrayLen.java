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

    /**
     * 滑动窗口, 左闭右开
     * @param target 目标数
     * @param nums  数组
     * @return  ...
     */
    public int minSubArrayLen02(int target, int[] nums) {
        int left = 0, right = 0;
        int ans = Integer.MAX_VALUE, sum = 0;
        while (left <= right && right < nums.length) {
            // 右扩张
            while (sum < target && right < nums.length) {
                sum += nums[right];
                right++;
            }
            // 左收缩
            while (sum >= target && left < right) {
                ans = Math.min(ans, right - left);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
