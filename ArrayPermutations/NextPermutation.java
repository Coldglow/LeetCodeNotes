// https://leetcode.cn/problems/next-permutation/
package ArrayPermutations;

import java.util.Arrays;

/**
 * @author David Wong
 * @date 09/03/2023 09:47
 * Scanner in = new Scanner(System.in);
 */
public class NextPermutation {
    /*
        这种序列题目可以把数组当作一个整数，要找到下一个序列，就是说找到一个比这个数大的数，并且尽可能的当前数近
        所以就是需要将后面的大数和前面的一个小数交换，并且大数和小数要尽可能的靠后，因此需要从后向前遍历
        算法流程：
        1. 从后向前找到第一个升序对nums[i]和nums[j]满足i < j,此时序列一定满足[j, end)为降序
        2. 在[j, end)范围内从后向前查找找到满足nums[k] > nums[i] 的第一个数nums[k]，满足j <= k < end
        3. 交换nums[i]和nums[k]
        4. 此时[j, end)仍然是降序的，将[j, end)变为升序即可
        如果在1.中没有找到i和j，说明此时整个队列是降序的，直接将整个队列排为升序即可。
     */
    public void nextPermutation(int[] nums) {
        int j = nums.length, i = 0, k = nums.length;
        // 找到满足条件的i 和 j
        while (--j > 0) {
            if (nums[j - 1] < nums[j]) {
                i = j - 1;
                break;
            }
        }
        // 如果没有找到，说明整个序列都是降序的，直接逆序返回即可
        if (j == 0) {
            reverseArr(nums, 0, nums.length - 1);
            return;
        }
        // 找到满足条件的k
        while (--k >= i) {
            if (nums[k] > nums[i]) {
                break;
            }
        }
        // 交换i 和 k
        int temp = nums[i];
        nums[i] = nums[k];
        nums[k] = temp;
        // 逆序[j, end)
        reverseArr(nums, j, nums.length - 1);
    }

    public void reverseArr(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation o = new NextPermutation();
        int[] arr = {2, 1, 4, 3};
        o.nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
    }
}
