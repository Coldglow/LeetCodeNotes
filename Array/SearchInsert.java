// https://leetcode.cn/problems/search-insert-position/
package Array;

public class SearchInsert {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == 0) {
            return nums[left] < target ? 1 : 0;
        } else {
            return nums[right] < target ? right + 1 : right;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 3, 5, 6};
        SearchInsert o = new SearchInsert();
        System.out.println(o.searchInsert(arr, 5));
    }
}
