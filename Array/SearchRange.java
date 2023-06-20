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

    /**
     * 2023年6月20日
     * 直接找两次, 第一次找出小于等于target的第一个数, 第二次找到大于target的第一个数
     * 然后合并即可
     * 对于小于等于的情况:
     * 1. nums[mid] < tar, left = mid + 1
     * 2. nums[mid] > tart, right = mid - 1
     * 3. nums[mid] == tar, right = mid - 1
     * -----------------
     * 对于大于的情况:
     * 1. nums[mid] < tar, left = mid + 1
     * 2. nums[mid] > tart, right = mid - 1
     * 3. nums[mid] == tar, right = mid + 1
     * ---------------------------
     * 综上, 分成mid + 1和mid - 1两种情况, 加一个boolean类型判断是小于等于还是大于
     * 所以可以写成
     * if (nums[mid] > tar || (lower && nums[mid] >= tar)) { right = mid - 1}
     * else { left = mid + 1}
     * ---------------------------
     * 那么如何在循环过程中记录结果呢?
     * 首先搞清楚这个结果是什么, 结果是
     * 小于等于tar的第一个数 lower && nums[mid] >= tar
     * 或者
     * 大于tar的第一个数 nums[mid] > tar
     * 那么什么时候会得到结果?
     * 根据上面的分类, 当循环条件不满足的时候, 上一次循环的mid就是结果
     * 因此需要在每次if条件满足的时候将mid记录下来, 等到下一次循环条件不满足的时候
     * 直接返回上一次的mid即可
     */
    public int[] searchRange02(int[] nums, int target) {
        // 第一次寻找 小于等于 tar 的第一个数的下标
        int left = search02(nums, target, true);
        // 第二次寻找 大于 tar 的第一个数的下标
        int right = search02(nums, target, false) - 1;
        // 只有当找到的nums[left] == target, 并且 res 不等于数组长度的时候, 才说明有结果
        // 当nums[left] == tar 的时候, 说明数组中存在等于tar的数, 否则说明tar比数组中的数都大
        // 这里就不用判断right了, 因为假如数组中存在tar, 那么left一定合法, right - 1也一定合法
        // 并且nums[right - 1] 一定等于tar
        // 如果数组中不存在tar, 那么left一定不合法, right也一定不合法, 所以二者判断一个即可.
        if (left <= right && nums[left] == target) {
            return new int[] {left, right};
        }
        return new int[] {-1, -1};
    }

    public int search02(int[] arr, int tar, boolean lower) {
        int left = 0, right = arr.length - 1, res = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > tar || (lower && arr[mid] >= tar)) {
                right = mid - 1;
                res = mid;  // 记录本次的mid, 当循环条件不满足的时候, 本次的mid记为要求的结果
            } else {
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
