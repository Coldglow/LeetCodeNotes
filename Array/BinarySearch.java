// https://leetcode.cn/problems/binary-search/
package Array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BinarySearch {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 如果是 [left, right] 这里就要加等于
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 2023年6月8日
     * 二分, 注意边界条件
     * @param nums 目标数组
     */
    public int binSearch(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        // 还是注意等于的情况
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int[] getRandomArray(int len, int max) {
        // 生成特定长度
        int[] arr = new int[len];
        Arrays.sort(arr);
        // 再随机填充数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 11;
        int max = 30;
        int[] arr1 = getRandomArray(len, max);
        System.out.println(Arrays.toString(arr1));
        BinarySearch obj = new BinarySearch();
        int randIndex = (int)(Math.random() * len + 1);
        System.out.println(randIndex);
        System.out.println(obj.search(arr1, arr1[randIndex]));
    }
}
