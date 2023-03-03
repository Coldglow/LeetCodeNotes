// https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 02/03/2023 10:06
 * Scanner in = new Scanner(System.in);
 */
public class MaxLengthOfRepeatedSubArray {
    /*
        1. 状态定义：dp[i][j]表示nums1 [0 ... j - 1]范围内和 nums2 [0 ... i - 1]范围内最长公共子数组的长度
           列表示nums1，行表示nums2
        2. 状态转移：
           i位置和j位置的值取决于nums1[i - 1]和nums2[j - 1]
           如果这两个相等，那么dp[i][j] = dp[i - 1][j - 1] + 1
        3. 初始化：
           dp[0][0] = 0
        4. 遍历顺序：
           左上角到右下角
     */
    public static int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int res  = 0;

        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        System.out.println("-------------");
        for (int i = 0; i < len2; i++) {
            for (int j = 0; j < len1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = {1,2,3,2,1};
        int[] arr2 = {3,2,1,4,7};
        findLength(arr1, arr2);
    }
}
