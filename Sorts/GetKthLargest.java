// https://leetcode.cn/problems/kth-largest-element-in-an-array/
package Sorts;

import java.util.Arrays;

public class GetKthLargest {
    /**
     * 有BUG
     * @param nums  ...
     * @param k ...
     * @return ...
     */
    public int findKthLargest(int[] nums, int k) {
        // 快排的思想，随机一个pivot
        // 如果pivot小于k，那么就排右边，直到pivot等于k
        // 否则排左边
        // 不按照返回数组那种写法，返回一个数
        quickSort(0, nums.length - 1, nums, nums.length - k);
        System.out.println(Arrays.toString(nums));
        return nums[nums.length - k];
    }

    public void quickSort(int L, int R, int[] arr, int k) {
        if (L < R) {
            int randP = L + (int)(Math.random() * (R - L + 1));
            System.out.println("random : " + randP);
            swap(arr, randP, R);
            int[] pivot = process(arr, L, R);
            if (randP != k) {
                if (randP < k ) {
                    quickSort(pivot[1] + 1, R, arr, k);
                } else {
                    quickSort(L, pivot[0], arr, k);
                }
            }
        }
    }

    public int[] process(int[] arr, int L, int R) {
        int less = L;
        int more = R;

        while (L != more) {
            if (arr[L] < arr[R]) {
                swap(arr, L++, less++);
            } else if (arr[L] == arr[R]) {
                L++;
            } else {
                swap(arr, --more, L);
            }
        }
        swap(arr, more, R);
        return new int[] {less, more};
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 堆排序，构建大根堆，当heapify到第k个数之后直接返回
     */
    public int  quickSort(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        int count = 1;
        while (heapSize > 0) {
            // 当进行到第k次，直接返回
            if (count == k) {
                break;
            }
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
            count++;
        }
        return arr[heapSize];
    }

    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int childLarger = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            int largest = arr[index] > arr[childLarger] ? index : childLarger;
            if (largest == index) {
                break;
            }
            swap(arr, index, childLarger);
            index = childLarger;
            left = 2 * index + 1;
        }
    }


    public static void main(String[] args) {
        GetKthLargest o = new GetKthLargest();
        int[] arr= new int[] {3,2,1,5,6,4};
        System.out.println(o.findKthLargest(arr, 2));
    }
}
