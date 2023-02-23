package BST;// https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/

import java.util.Arrays;

public class FIndMin {
    /**
     * 一路向左，找到树中最左边的节点
     * 如果此节点是最后一个元素，那么返回nums[0]
     * 否则向右遍历，直到找到一个比它小的元素
     * @param nums nums
     * @return n
     */
    public int findMin(int[] nums) {
        int index = 0;
        int len = nums.length;
        while (2 * index + 1 < len) {
            if (nums[index] > nums[2 * index + 1]) {
                break;
            }
            index = 2 * index + 1;
        }

        int res = nums[index];
        while (++index < len) {
            if (nums[index] < res) {
                res = nums[index];
                break;
            }
        }
        return index == len ? nums[0] : res;
    }

    /**
     * 直接二分
     * @param nums nums
     * @return n
     */
    public int findMin2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int pivot;
        while (low < high) {
            pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

    public static int[] getRandomArray(int len, int max) {
        // 生成特定长度
        int[] arr = new int[(int)(Math.random() * len) + 1];
        // 再随机填充数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * max) + 1;
        }
//        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 50;
        int[] arr = getRandomArray(len, max);
        System.out.println(Arrays.toString(arr));
        FIndMin obj = new FIndMin();
        System.out.println(obj.findMin(arr));
    }
}
