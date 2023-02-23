// https://leetcode.cn/problems/search-in-rotated-sorted-array/
package Array;

public class SearchInRotatedSortedArray {
    /*
        将一个数组一分为二，其中一定有一份是顺序递增的
        判断哪一份是递增的，然后判断target是否在其中
        不在的话去另一半找
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 1 && nums[0] == target) {
            return 0;
        }
        int L = 0, R = nums.length - 1;
        int n = nums.length - 1;
        while (L <= R) {
            int M = L + ((R - L) >> 1);
            if (nums[M] == target) {
                return M;
            }
            if (nums[0] <= nums[M]) {  // 如果左半部分是递增的
                // 如果target位于左半部分
                if (nums[0] <= target && nums[M] > target) {
                    R = M - 1;
                } else {
                    // 如果target位于右半部分
                    L = M + 1;
                }
            } else {   // 如果右半部分是递增的
                if (nums[n] >= target && nums[M] < target) {
                    // 如果target位于右半部分
                    L = M + 1;
                } else {
                    // 如果target位于左半部分
                    R = M - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5,1,3};
        System.out.println(search(arr, 3));
    }
}
