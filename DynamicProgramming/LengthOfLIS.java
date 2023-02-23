package DynamicProgramming;// https://leetcode.cn/problems/longest-increasing-subsequence/

import java.util.Arrays;

public class LengthOfLIS {
    // dp[i] 表示 [0 ... i]位置的最长递增序列的长度
    // 初始dp都为1，表示单独一个元素的长度
    // 对于每个位置，都从0位置开始找
    public int lengthOfLis(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 一定注意这里，是i位置和j位置加一后 二者取较大值
                    // 不是单独的j位置+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 数组cell用来保存最长上升子序列
     * 对原数组进行遍历，将每个元素二分插入到cell中
     * 如果cell中的元素都比当前元素小，当前元素直接加入到cell末尾
     * 否则，替换掉大于等于它的元素中最小的那个
     * cell数组必定升序，所以可以通过二分查找应该替换哪个，相对于dp，提升了查找的速度
     * 时间复杂度O(NLogN)
     * @param nums  nums
     * @return 长度
     */
    public int lengthOfLis2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] cell = new int[nums.length];
        int res = 0;   // cell数组中的有效长度
        for (int num : nums) {
            int l = 0, r = res;
            while (l < r) {
                int m = (r + l) / 2;
                if (cell[m] < num) {  // num比中值大
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            cell[l] = num;
            if (res == r) {   // 说明没有比num小的数，直接放在数组最后一位
                res++;
            }
        }
        return res;
    }


    public static int[] getRandomArray(int len, int max) {
        // 生成特定长度
//        int[] arr = new int[(int)(Math.random() * len) + 1];
        int[] arr = new int[len];
        // 再随机填充数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
//        System.out.print("...");
        int len = 11;
        int max = 50;
        int[] arr = getRandomArray(len, max);
//        int[] arr = new int[] {6, 9, 44, 37, 30, 20, 26, 5, 2, 45, 35};
        System.out.println(Arrays.toString(arr));
        LengthOfLIS obj = new LengthOfLIS();
        System.out.println(obj.lengthOfLis2(arr));
    }
}
