// https://leetcode.com/problems/wiggle-subsequence/
package Greed;

public class WiggleSequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        // -1 表示上一个差值是负的  0 表示相等  1 表示正差值
        int lastDiff = nums[1] - nums[0];
        int res = lastDiff == 0 ? 1 : 2;
        // 从第三个数开始遍历
        for (int i = 2; i < nums.length; ++i) {
            int curDiff = nums[i] - nums[i - 1];
            // 这里不能直接写成 cue != last
            // 因为当cur == 0  last ！= 0这种情况，不应该增加结果数量
            if ((curDiff > 0 && lastDiff <= 0) || (curDiff < 0 && lastDiff >= 0)) {
                res++;
                lastDiff = curDiff;   // 只有在差值变化的时候才更新
            }
        }
        return res;
    }

    //
    public int wiggleMaxLength2(int[] nums) {
       int up = 1, down = 1;
       for (int i = 1; i < nums.length; ++i) {
           // 如果nums[i] > nums[i-1]
           // 说明当前是从 谷到峰  up = down + 1
           if (nums[i] > nums[i - 1]) {
               up = down + 1;
           }
           // 说明当前是从 峰到谷  down = up + 1
           if (nums[i] < nums[i - 1]) {
               down = up + 1;
           }
           // 等于不做处理，是过渡元素
       }
       return nums.length == 0 ? 0 : Math.max(up, down);
    }


    public static void main(String[] args) {
        int[] arr = new int[] {92,0,160,70,172,292,9,64,156,153,26,145,196,222};
        WiggleSequence o = new WiggleSequence();
        System.out.println(o.wiggleMaxLength(arr));
    }
}
