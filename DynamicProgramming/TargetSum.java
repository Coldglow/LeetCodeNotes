// https://leetcode.com/problems/target-sum/
package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 2023.2.23
 */

public class TargetSum {
    // 先尝试暴力
    private int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        backTracking(nums, target, 0, 0);
        return res;
    }

    public void backTracking(int[] nums, int target, int sum, int i) {
        if (i == nums.length) {
            if (sum == target) {
                res++;
            }
        } else {
            backTracking(nums, target, sum + nums[i], i + 1);
            backTracking(nums, target, sum - nums[i], i + 1);
        }
    }

    // 背包
    // 设数组总和为sum，添加负号的元素和为neg，则添加正号的数的和为sum-neg
    // 于是有 （sum-neg）- neg = target
    // neg = (sum - target) / 2
    // 这里有个条件，就是sum - target一定要是   非负偶数
    // 非负：因为数组中的数都是正数，所以他们的部分和也一定是正数
    // 偶数：假如sum-target是奇数，那么neg一定不存在，因为neg * 2 = sum - target
    // 于是问题就转化成了 挑选数组中的数，使得选中的数的和是neg，一共有多少种方式
    // 和之前不同的地方在于这里要求的是组合数量

    // 状态定义：因此定义dp[j]表示装满容量为j的背包有多少种方式
    // 递归方程：dp[j] += dp[j - nums[i]]  注意是 += 不是等于，因为是组合问题
    //      例如：dp[j]，j 为5，
    //      已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 容量为5的背包。
    //      已经有一个2（nums[i]） 的话，有 dp[3]种方法 凑成 容量为5的背包。
    //      已经有一个3（nums[i]） 的话，有 dp[2]中方法 凑成 容量为5的背包
    //      已经有一个4（nums[i]） 的话，有 dp[1]中方法 凑成 容量为5的背包
    //      已经有一个5 （nums[i]）的话，有 dp[0]中方法 凑成 容量为5的背包
    //      那么凑整dp[5]有多少方法呢，也就是把 所有的 dp[j - nums[i]] 累加起来。
    //      所以求组合类问题的公式，都是类似这种：dp[j] += dp[j - nums[i]]
    // 初始化：
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        int n = sum - target;
        if (n < 0 || (n & 1) == 1) {
            return 0;
        }
        int capacity = n / 2;
        int[] dp = new int[capacity + 1];
        dp[0] = 1;
        for (int num : nums) {
            // 注意滚动循环这里的条件
            for (int j = capacity; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[capacity];
    }



    public static void main(String[] args) {
        int[] arr = new int[] {1,1,1,1,1};
        TargetSum o = new TargetSum();
        System.out.println(o.findTargetSumWays(arr, 3));
    }
}
