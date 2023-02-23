package Sorts;

public class MergeSort {

    public void mergeSort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    public void partition(int[] nums, int L, int R) {
        // 如果L == R  说明长度为1
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        // 二分的过程
        partition(nums, L, M);
        partition(nums, M + 1, R);
        // 归并的过程
        merge(nums, L, M, R);
    }

    // nums数组中[L, M] 和 [M + 1, R] 都是有序的
    // 现在希望从 [L, R]范围内有序
    public void merge(int[] nums, int L, int M, int R) {
        // 开一个R-L+1大小的辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = M + 1;   // p1指向数组左侧部分   p2指向数组右侧部分
        while (p1 <= M && p2 <= R) {
            help[i++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }

        while (p1 <= M) {
            help[i++] = nums[p1++];
        }
        while (p2 <= R) {
            help[i++] = nums[p2++];
        }

        // 写回原数组
        for (int k : help) {
            nums[L++] = k;
        }
    }

    public static void main(String[] args) {

    }
}
