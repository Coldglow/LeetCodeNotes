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
}
