package Sorts;

import java.util.Arrays;

public class QuickSort {
    public void quickSort(int[] nums, int L, int R) {
        if (L < R) {
            swap(nums, L + (int)(Math.random() * (R - L + 1)), R);
            int[] pivot = process(nums, L, R);
            quickSort(nums, L, pivot[0]);
            quickSort(nums, pivot[1] + 1, R);
        }
    }

    public int[] process(int[] arr, int L, int R) {
        int less = L;
        int more = R;
        // 如果L位置的值小于R位置的值，那么L位置的值和小于区域的下一个数交换，小于区域++
        // 如果L位置的值等于R位置的值，那么L直接++
        // 如果L位置的值大于R位置的值，那么more区域先--，L位置的值和大于区域的前一个数交换
        while (L != more) {
            if (arr[L] < arr[R]) {
                swap(arr, L++, less++);
            } else if (arr[L] == arr[R]) {
                L++;
            } else {
                swap(arr, --more, L);
            }
        }
        // while结束，[L, less)表示小于区域
        // [less, more]表示等于区域
        // (more, R]表示大于区域
        // 最后还需要把arr[R]和more区域的第一个数交换
        swap(arr, R, more);
//        System.out.println("randI: " + randI);
//        System.out.println("less: " + less + " arr[less]: " + arr[less] + " more:" + more + " arr[more]:" + arr[more]);
//        System.out.println(Arrays.toString(arr));
        return new int[] {less, more};
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,1,5,6,4, 7, 8, 5, 8, 5};
        QuickSort o = new QuickSort();
        o.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
