// https://leetcode.com/problems/jump-game-ii/
package Greed;

public class JumpGame_2 {
    /**
     * 和上一个不一样的是，找到跳跃的最少次数
     * 所以可以在i到mostRight范围内找到最大的数，跳到该数
     * 每跳一次都检查一次
     * @param nums nums
     * @return n
     */
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int step = 0;
        int i = 0;
        while (i < nums.length - 1) {
            if (i + nums[i] < nums.length - 1) {
                i = getMostRight(nums, i);
            } else {
                i += nums[i];
            }
            step++;
        }
        return step;
    }

    // 返回该步数范围内最大的值
    public int getMostRight(int[] nums, int start) {
        int maxIndex = start;
        for (int step = 1; step <= nums[start] && step + start < nums.length; ++step) {
            if (nums[maxIndex] <= nums[step + start]) {
                maxIndex = start + step;
            }
        }
        return maxIndex;
    }

    public int jump2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int step = 0;
        int i = 0;
        while (i < nums.length - 1 && i + nums[i] < nums.length - 1) {
            int start = i;
            int max = nums[i];
            for (int cur = i + 1; cur < nums.length && max > 0; ++cur, --max) {
                if (nums[cur] >= nums[i]) {
                    i = cur;
                }
            }
            if (start == i) {
                i += nums[i];
            }
            step++;
        }
        return step;
    }

    public int jump_answer(int[] nums) {
        int step = 0;
        int start = 0;
        int end = 0;
        while (end < nums.length - 1) {
            int mostRight = 0;
            // 每次在都从当前位置到mostRight位置更新mostRight的值
            for (int i = start; i <= end; ++i) {
                mostRight = Math.max(mostRight, i + nums[i]);
            }
            start = end + 1;
            end = mostRight;
            step++;
        }
        return step;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,1,1,1};
        JumpGame_2 o = new JumpGame_2();
        System.out.print(o.jump_answer(arr));
    }
}
