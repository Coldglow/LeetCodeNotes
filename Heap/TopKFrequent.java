// https://leetcode.com/problems/top-k-frequent-elements/
package Heap;

import java.util.*;

public class TopKFrequent {

    static class AComp implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] - o2[1];
        }
    }

    /**
     * 仍然是堆的思想，刚开始想的是遍历一遍数组，一边遍历一边修改值
     * 但是这样就要自己手写堆了
     * 看了答案之后知道可以遍历一遍数组统计词频
     * 然后遍历词频数组维护大小为k的小根堆
     * @param nums  nums
     * @param k k
     * @return  k
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(new AComp());
        // 遍历数组 统计词频
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 1) + 1);
        }
        // 遍历词频数组，维护小根堆的大小为k
        for (int n : map.keySet()) {
            if (heap.size() == k) {
                if (heap.peek()[1] < map.get(n)) {
                    heap.poll();
                    heap.add(new int[] {n, map.get(n)});
                }
            } else {
                heap.add(new int[] {n, map.get(n)});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = heap.poll()[0];
        }
        return res;
    }

    // 构建一个大根堆
    public void heapSort(int[] arr) {

        // 堆排的基础是堆结构，所以要先从0位置开始将数组变成一个堆结构
        for (int i = arr.length; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
        System.out.println(Arrays.toString(arr));
    }

    // heapInsert是向上查询
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // heapify是向下查询
    public void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            // 取出孩子节点的最大值的下标
            int larger = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            // 然后比较larger和index的值,这样larger就是三者的最大值
            larger = arr[index] > arr[larger] ? index : larger;
            if (larger == index) {
                break;
            }
            swap(arr, index, larger);
            index = larger;
            left = 2 * index + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {8,4,15,9,20,2,4};
        TopKFrequent o = new TopKFrequent();
        o.heapSort(arr);
//        int[] res = topKFrequent(arr, 2);
//        System.out.println(Arrays.toString(res));
    }
}
