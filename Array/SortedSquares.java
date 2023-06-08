// https://leetcode.com/problems/squares-of-a-sorted-array/
package Array;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortedSquares {
    /**
     * 先通过二分找到距离0最近的数
     * 然后向左右两边移动
     * @param nums nums
     * @return  nums
     */
    public int[] sortedSquares(int[] nums) {
        if (nums.length == 0) {
            return new int[] {};
        }

        int l = 0;
        int r = nums.length - 1;
        int m = (l + r) >> 1;
        int len = nums.length;
        int[] res = new int[len];
        // 找出小于等于0的第一个数，防止出现多个0
        // 如果存在0，则m指向小于0的第一个数，此时循环结束后m需要加一
        // 如果不循在m，则m指向大于0的第一个数，此时m需要减一
        while (l <= r) {
            m = (l + r) >> 1;
            if (nums[m] >= 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        // 防止-4,-1,0,3,10 这种情况  有0，但是因为上述等于情况也会继续循环
        // 所以m会继续左移
        if (m + 1 < len && nums[m] + nums[m + 1] < 0) {
            m++;
        }
        // 防止 -2 -1 3 这种情况，因为没有0，所以结束后m指向3
        if (m - 1 > -1 && nums[m] + nums[m - 1] > 0) {
            m--;
        }
        // 下面就是从m向两边移动，哪个小就把哪个加到数组中
        int index = 0;
        res[index++] = nums[m] * nums[m];
        l = m - 1;
        r = m + 1;
        while (l > -1 && r < len) {
            if (-nums[l] < nums[r]) {
                res[index++] = nums[l] * nums[l];
                l--;
            } else {
                res[index++] = nums[r] * nums[r];
                r++;
            }
        }
        while (l > -1) {
            res[index++] = nums[l] * nums[l];
            l--;
        }
        while (r < nums.length) {
            res[index++] = nums[r] * nums[r];
            r++;
        }
        return res;
    }

    /**
     * 双指针，由于已经知道结果有多少，所以可以直接头尾双指针比较大小
     * 然后逆序存放即可
     * @param nums nums
     * @return nums
     */
    public int[] sortedSquares2(int[] nums) {
        int[]res = new int[nums.length];
        int L = 0, R = nums.length - 1, index = R;
        while (L <= R) {
            if (Math.abs(nums[L]) > nums[R]) {
                res[index] = nums[L] * nums[L];
                L++;
            } else {
                res[index] = nums[R] * nums[R];
                R--;
            }
            index--;
        }
        return res;
    }

    /**
     * 2023年6月8日
     * 直接双指针, 从大到小的顺序填充
     * @param nums nums
     * @return ...
     */
    public int[] sortedSquares02(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1, i = nums.length - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                res[i] = nums[left] * nums[left];
                left++;
            } else if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                res[i] = nums[right] * nums[right];
                right--;
            }
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {-4, -1, 0, 3, 10};
        SortedSquares obj = new SortedSquares();
        int[] res = obj.sortedSquares2(arr1);
        System.out.println(Arrays.toString(res));
    }
}
