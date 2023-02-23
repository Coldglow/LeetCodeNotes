// https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
package Array;

import java.util.Arrays;

public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        // 表示找 小于等于 target的第一个数
        int leftIndex = search(nums, target, true);
        // 表示找 大于 target的第一个数
        int rightIndex = search(nums, target, false) - 1;
        System.out.println(leftIndex);
        System.out.println(rightIndex);
        // 如果target比最小值小，则rightIndex
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target) {
            return new int[] {leftIndex, rightIndex};
        }
        return new int[] {-1, -1};
    }
    // 找到小于等于target的第一个数和大于target的第一个数
    public int search(int[] nums, int target, boolean lower) {
        int left = 0;
        int right = nums.length - 1;
        int res = nums.length;   // 注意这里是一定要写长度而且不减1  防止出现1的情况
        while (left <= right) {
            int mid = (right + left) >> 1;
            // 如果要找大于target的第一个数，并且此时mid大于target，right左移
            // 如果要找小于等于target的第一个数，并且此时mid大于等于target，也是right左移
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                res = mid;
            } else {
                // 如果不是
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int len = 20;
//        int max = 30;
//        int[] arr1 = getRandomArray(len, max);
//        System.out.println(Arrays.toString(arr1));
        int[] arr1 = new int[] {1};
        SearchRange obj = new SearchRange();
        int[] res =  obj.searchRange(arr1, 1);
        System.out.println(Arrays.toString(res));
    }
}
