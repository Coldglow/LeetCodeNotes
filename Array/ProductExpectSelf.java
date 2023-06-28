// https://leetcode.cn/problems/product-of-array-except-self/
package Array;

/**
 * @author David Wong
 * @date 28/06/2023 14:49
 * Scanner in = new Scanner(System.in);
 */
public class ProductExpectSelf {
    /**
     * 两次遍历, 分别得到i左侧的数的和, 再得到i右侧的数的和, 然后两次和相乘
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];
        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        for (int i = n - 2; i > -1; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }
}
