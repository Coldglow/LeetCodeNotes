package Array;

/**
 * @author David Wong
 * @date 28/03/2023 09:28
 * Scanner in = new Scanner(System.in);
 */
public class FindMedianSortArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        int midIndex = n / 2; // 找到第midIndex大的数
        if ((n & 1) == 1) {
            // 奇数的情况下找一次即可
            // 注意这里的参数是第K大的数，不是说下标为K的数
            return getKth(nums1, nums2, midIndex + 1);
        } else {
            // 偶数的情况要找两次
            double median = getKth(nums1, nums2, midIndex) + getKth(nums1, nums2, midIndex + 1);
            return median / 2.0;
        }
    }

    public int getKth(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int index1 = 0, index2 = 0;
//        int kthElement = 0;

        while (true) {
            // 说明A数组已经全都排除完毕
            // 那么第K大的数就一定是B数组中下标为index2 + k - 1的数
            if (index1 == len1) {
                return nums2[index2 + k - 1];
            }
            // 如果B数组全都排除完毕，同理
            if (index2 == len2) {
                return nums1[index1 + k - 1];
            }
            // 说明此时已经都排除完了，选择A和B中剩余的数中较小的那个
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 循环的情况
            int half = k / 2;
            // 确保下标不越界
            int newIndex1 = Math.min(index1 + half, len1) - 1;
            int newIndex2 = Math.min(index2 + half, len2) - 1;
            // 找到剩余A和B中的第k/2个数
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            // k减去对应的值
            // newIndex - index + 1表示这么多个数被排除了
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
