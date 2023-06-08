// https://leetcode.cn/problems/remove-element/
package Array;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int r = nums.length - 1;
        int n = 0;
        // 等于防止所有元素都是val的情况
        while (n <= r) {
            if (nums[n] == val) {
                nums[n] = nums[r];
                nums[r] = val;
                r--;
            } else {
                n++;
            }
        }
        return n;
    }

    /**
     * 2023年6月8日
     * 其实就是把目标值放在数组最后面
     * @param nums 目标数组
     * @param val 目标元素
     * @return ...
     */
    public int removeElement02 (int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
                continue;
            }
            nums[left] = nums[right];
            right--;
        }
        return left;
    }
}
