// https://leetcode.com/problems/partition-equal-subset-sum/
package DynamicProgramming;

import java.util.Arrays;

public class PartitionEqualSubSetSum {
    // 该问题是01背包问题的应用
    // 首先确定以下几点点
    // 1. 背包的体积:  sum / 2
    // 2. 要放入背包的商品：nums[i]
    // 3. 商品的重量：nums[i]
    // 4. 商品的价值：nums[i]
    // 5. 商品不可重复放入（01背包，否则是完全背包）
    // 6. 如果正好装满，说明找到了总和为2/sum的子集
    // 状态定义 dp[j] 表示选取[0 .. j]之间任意数字之和
    // 则状态转移方程也是 max(dp[j], dp[j - weight[i]] + value[i])
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // 总数为奇数，不能平分
        if (sum % 2 != 0) {
            return false;
        }
        int capacity = sum / 2;
        // 如果子集和正好是capacity，说明可以找到
        // 否则找不到
        int[] dp = new int[capacity + 1];
        for (int num : nums) {   // 遍历物品
            // 注意这逆序求，因为内层循环是遍历容量，因此当j<num的时候就不能添加了
            for (int j = capacity; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
        }
        return dp[capacity] == capacity;
    }
}
