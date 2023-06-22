// https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 22/06/2023 11:08
 * Scanner in = new Scanner(System.in);
 */
public class FindLength {
    /**
     * 注意子数组是连续的, 子序列是不连续的
     * 1. 状态定义: dp[i][j] 表示 nums1[0 ... i] 和 nums2[0 ... j] 之间最长子数组的长度
     * 2. 状态转移:
     *    如果 nums1[i] == nums2[j], dp[i][j] = dp[i - 1][j - 1] + 1
     *    如果不相等, 那么dp[i][j] = 0
     * 3. 初始化:
     *    dp数组第一行, dp[0][j] 表示nums1[0] 和nums2的最长公共子数组的长度
     *    所以如果nums1[0] == nums2[j], dp[0][j] = 1, 如果不相等的话就是0
     *    dp数组第一列同理
     * 4. 返回dp数组中的最大值
     * ----------------------------------
     * 通过上面的而分析可以得出, dp[i][j]只雨dp[i-1][j-1]有关, 因此可以使用滚动数组实现
     * 并且因为状态与前一位有关, 所以内层循环要从右向左遍历, 同时数组多开一位, 即dp[n2 + 1]
     * 防止dp[0] = dp[-1] + 1的情况
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int n2 = nums2.length;
        int[] dp = new int[n2 + 1];
        int res = 0;

        for (int k : nums1) {
            for (int j = n2; j > 0; j--) {
                dp[j] = k == nums2[j - 1] ? dp[j - 1] + 1 : 0;
                res = Math.max(res, dp[j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {0,1,1,1,1};
        int[] nums2 = new int[] {1,0,1,0,1};
        int res = findLength(nums2, nums1);
        System.out.println(res);
    }
}
