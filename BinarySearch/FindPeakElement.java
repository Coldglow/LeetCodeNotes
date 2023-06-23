// https://leetcode.cn/problems/find-peak-element/
package BinarySearch;

/**
 * @author David Wong
 * @date 23/06/2023 15:09
 * Scanner in = new Scanner(System.in);
 */
public class FindPeakElement {
    /**
     * 就直接二分, 将while循环改成递归即可
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1 || nums[0] > nums[1]) {
            return 0;
        }
        int n = nums.length;
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }
        return search(1, n - 2, nums);
    }

    public int search(int left, int right, int[] nums) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        }
        int ans1 = search(mid + 1, right, nums);
        if (ans1 != -1) {
            return ans1;
        }
        return search(left, mid - 1, nums);
    }

    public static void main(String[] args) {
        FindPeakElement obj = new FindPeakElement();
        int[] arr = new int[] {3,4,3,2,1};
        System.out.println(obj.findPeakElement(arr));
    }
}
