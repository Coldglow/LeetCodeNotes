package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 找到数组中第K大的元素
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 */
public class FindKthLargest {
    /**
     * 建立K大小的小根堆，如果堆满，弹出堆顶的最小值
     * 遍历结束后一定会找到最大的K个元素，堆顶元素就是第K大的元素
     * @param nums  nums
     * @param k k
     * @return k
     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Integer num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();
    }

    /**
     * 快排的思想，修改后的快速选择算法 相较于上面的堆实现更快
     * 如果某次选择pivot下标等于 nums.length - k，说明左边的数都比pivot小，右边的都比pivot大
     * 也就是第K大数
     * 需要随机选择pivot
     * @param nums nums
     * @param k k
     * @return kk
     */
    public int findKthLargestPartition(int[] nums, int k) {
        quickSelect(nums, nums.length - k, 0, nums.length - 1);
        return nums[nums.length - k];
    }

    public void quickSelect(int[] nums, int k, int L, int R) {
        if (L < R) {
            int p = partition(nums, L ,R);
            if (p == k) {
                return;
            } else if (p < k) {
                quickSelect(nums, k, p + 1, R);
            } else {
                quickSelect(nums, k, L, p - 1);
            }
        }
    }

    public int partition(int[] nums, int L, int R) {
        int pivot = L + (int)(Math.random() * (R - L + 1));
        swap(nums, pivot, R);
        int more = R;
        while (L != more) {
            if (nums[L] <= nums[R]) {
                L++;
            } else {
                swap(nums, L, --more);
            }
        }
        swap(nums, L, R);
        return L;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void heapManually(int[] arr) {



        // 一次heapify操作
        for (int i = arr.length; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 下面是堆排序，每次进行一次heapify都会得到一个最大值在堆顶
        // 然后将堆顶元素和数组有效范围的最后一个数交换，继续
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public void heapInsert(int[] arr, int index) {
        while ((index - 1) / 2 >= 0 && arr[(index - 1) / 2] > arr[index]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 大根堆
     * @param arr  d
     * @param index d
     * @param heapSize d
     */
    public void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int larger = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            larger = arr[index] > arr[larger] ? index : larger;
            if (larger == index) {
                return;
            }
            swap(arr, index, larger);
            index = larger;
            left = 2 * index + 1;
        }
    }

    public static int[] getRandomArray(int len, int max) {
        // 生成特定长度
//        int[] arr = new int[(int)(Math.random() * len) + 1];
        int[] arr = new int[10];
        // 再随机填充数
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * max) + 1;
        }
        return arr;
    }


    public static void main(String[] args) {
        FindKthLargest obj = new FindKthLargest();
        int len = 10;
        int max = 50;
//        int testTime = 10000;   // 测试轮数
//        for (int i = 0; i < testTime; i++) {
//            int[] arr = getRandomArray(len, max);
//            int arr1 = obj.findKthLargest(arr, 3);
//            int arr2 = obj.findKthLargestPartition(arr, 3);
//            for (int j = 0; j < arr.length; j++) {
//                if (arr1 != arr2) {
//                    System.out.print("******");
//                    break;
//                }
//            }
//        }
        int[] arr = new int[] {21, 28, 9, 28, 36, 19, 29, 14, 42, 34};
        System.out.println(Arrays.toString(arr));
        obj.heapManually(arr);
        System.out.println(Arrays.toString(arr));
    }
}
