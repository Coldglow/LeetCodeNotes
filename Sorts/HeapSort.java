package Sorts;

public class HeapSort {

    public void heapSort(int[] arr) {
        // 刚开始arr不是堆结构
        // 先把arr变成堆结构
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 循环完成后数组arr已经成了大根堆结构
        // 然后进行多次heapify操作，将最大值放在堆尾部
        // 直到heapsize减为0，就排好序了
        int heapSize = arr.length;
        // 破坏堆结构，取出堆顶元素
        swap(arr, 0 , --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);   // 堆顶元素下沉
            swap(arr, 0, --heapSize);   // 此时堆顶元素是最大值，交换到尾部
        }
    }

    // 构建大根堆的过程叫做heapInsert过程
    // 在数组中最后一个的位置插入，然后和父节点比较
    // 如果大于父节点则交换
    // 否则停止
    // 父节点(index - 1) / 2
    public void heapInsert(int[] arr, int index) {
        // 不用判断越界，因为java中负数除正数等于0
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // heapify操作是指：取出堆顶数据，然后将其移除，用堆的最后一个数字代替，然后将剩余的数字维护成一个堆结构
    // 具体做法：
    //      维护一个变量heapSize，记录此时堆中还有多少数据
    //      将堆顶元素和最后一个元素交换
    //      heapSize-- 表示最后一个元素已经被取出
    //      调整堆结构，从堆顶开始比较，看当前元素是否能向下移动
    //          先得到孩子节点中的较大值，然后和当前值比较，如果当前值大，则循环结束
    //          否则交换，更新index下标继续循环此步骤
    //      回到第二步继续循环，直到heapSize减为0
    // 如果是大根堆，经过多次heapify操作之后，会按照递增顺序排列，因为最大的值总会放在堆后面
    // 如果是小根堆，则会按照递减顺序排列
    public void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            // 只有当存在右节点并且右节点大于左节点的时候菜返回右节点的下标
            // 否则都返回左节点的下标
            int childLargerIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            int largestIndex = arr[index] > arr[childLargerIndex] ? index : childLargerIndex;
            if (largestIndex == index) {
                break;
            }
            swap(arr, index, childLargerIndex);
            index = childLargerIndex;
            left = 2 * index + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
