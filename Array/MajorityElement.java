// https://leetcode.com/problems/majority-element/
package Array;

public class MajorityElement {
    /**
     * 可以排序，然后直接返回下标为 n/2 位置的元素  证明略
     * 也可以用投票算法:
     *      维护一个count和val
     *      如果cur == val   count++
     *      如果cur ！=val    count--
     *      如果count == 0   val = cur
     * 遍历结束后的val就是众数
     * @param nums num
     * @return 众数
     */
    public int majorityElement(int[] nums) {
        int count = 1;
        int val = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            if (val == nums[i]){
                count++;
            } else if (--count <= 0) {
                val = nums[i];
                count = 1;
            }
        }
        return val;
    }

    public static void main(String[] args) {
        int[] arr = {2,2,1,1,1,2,2};
        MajorityElement o = new MajorityElement();
        System.out.println(o.majorityElement(arr));
    }
}
