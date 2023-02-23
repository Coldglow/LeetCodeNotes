// https://leetcode.com/problems/move-zeroes/
package Array;

public class moveZeros {
    // 还是双指针
    public void moveZeros(int[] nums) {
        int s = 0;
        int f = 0;
        while (f < nums.length) {
            if (nums[f] != 0) {
                int temp = nums[s];
                nums[s++] = nums[f];
                nums[f++] = temp;
            }
            f++;
        }
    }
}
