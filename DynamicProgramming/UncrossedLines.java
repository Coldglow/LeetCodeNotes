// https://leetcode.cn/problems/uncrossed-lines/
package DynamicProgramming;

/**
 * @author David Wong
 * @date 03/03/2023 10:36
 * Scanner in = new Scanner(System.in);
 */
public class UncrossedLines {
    /*
        其实就是最长公共子序列
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                dp[i][j] = nums1[i - 1] == nums2[j - 1] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.println("-------------");
        for (int i = 1; i < n1 + 1; i++) {
            for (int j = 1; j < n2 + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[n1][n2];
    }

    public static void main(String[] args) {
        int[] arr1 = {2,5,1,2,5};
        int[] arr2 = {10,5,2,1,5,2};
        maxUncrossedLines(arr1, arr2);
    }
}
