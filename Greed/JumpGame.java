// https://leetcode.com/problems/jump-game/
package Greed;

public class JumpGame {

    public boolean canJump(int[] nums) {
        int mostRight = 0;  // 表示能跳到的最远距离
        for (int i = 0; i < nums.length; ++i) {
            if (i > mostRight) {
                return false;
            }
            // i + nums[i] 表示从i位置能调到的最远距离
            mostRight = Math.max(mostRight, i + nums[i]);
//            if (mostRight >= nums.length - 1) {
//                return true;
//            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] arr = new int[] {2,3,1,1,4};
        JumpGame o = new JumpGame();
        System.out.println(o.canJump(arr));
    }
}
