// https://leetcode.com/problems/last-stone-weight-ii/
package DynamicProgramming;

import java.util.Arrays;

/**
 * 2023.2.23
 */
public class WeightOfLastStone {
    /*
        这道题的本质就是尽量将数组分成两个和相近的两部分
        同样可以转化成01背包问题
        那么就取背包容量为和的一半，得到容量一半的时候能够装的最大值
        然后用sum-dp[sum/2]得到剩下的另一半，再将这两部分相撞即可
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int stone : stones) {
            // 注意内循环这里，当容量小于当前石头的重量的时候就已经装不下了，可以不用继续循环了
            // 所以是j >= stone，而不是j > 0
            for (int j = target; j >= stone; j--) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }
        return sum - dp[target] - dp[target];
    }
}
