// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
package Array;

public class RemoveDuplicates {
    // 双指针
    public int removeDuplicates(int[] nums) {
        int s = 0;
        int f = 0;
        while (f < nums.length) {
            if (nums[s] != nums[f]) {
                int temp = nums[++s];
                nums[s] = nums[f];
                nums[f] = temp;
            }
            f++;
        }
        return s + 1;
    }
}
