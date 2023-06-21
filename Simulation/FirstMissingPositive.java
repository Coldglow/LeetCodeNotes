// https://leetcode.cn/problems/first-missing-positive/
package Simulation;

/**
 * @author David Wong
 * @date 10/04/2023 15:08
 * Scanner in = new Scanner(System.in);
 */
public class FirstMissingPositive {
    // 首先得明白一件事情，长度为N的数组，其答案在[1, N+1]的范围内
    // 由于数组的长度为N，那么可以利用这个性质，先将数组中的负数都变成N+1
    // 此时数组中的数都是正数
    // 遍历数组，对于nums[i],将i位置的数变成负数，如果已经是负数，则不用变
    // 结束后再次数组，找到第一个正数所在的下标i，i+1即为结果
    // 如果所有数都是负数，那么返回N+1
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 将所有非正数都变成N+1
        for (int i = 0; i < n; i++) {
            if (nums[i] < 1) {
                nums[i] = n + 1;
            }
        }
        // 将nums[i]位置的数变成负数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        // 找到第一个为正数的下标
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n  + 1;
    }
}
