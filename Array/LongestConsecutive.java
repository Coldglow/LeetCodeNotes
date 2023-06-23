// https://leetcode.cn/problems/longest-consecutive-sequence/
package Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

/**
 * @author David Wong
 * @date 23/06/2023 14:02
 * Scanner in = new Scanner(System.in);
 */
public class LongestConsecutive {
    /**
     * 不完整的分析:
     * nums长度为n, 那么答案一定小于等于n, 那么可以申请数组 arr[n], 初始化为-1, 遍历nums, 所有数对n取模
     * arr[num % n] = num, 但是对于冲突的部分无法处理, 后遍历的会覆盖先遍历的, 如何解决这个问题
     * --------------------------
     * 看解析:
     * 给的答案和我自己想的思路完全不一样.
     * 首先使用set去重, 然后双循环遍历, 只有当外层循环是一个序列的第一个数的时候才近入内层循环.
     * 如何判断当前数num是是不是一个连续序列的第一个数:
     *  判断 num - 1是否在集合中, 如果在, 说明不是第一个数, 跳过
     *  如果不在, 说明是, 进入内层循环, 从num + 1开始找, 找的到就继续, 找不到就跳出
     * 这样总的来说, 虽然有两层for循环, 但是时间复杂度依然是O(n)
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            int len = 1;
            // 判断是否存在num - 1, 如果存在, 说明num不是一个连续序列的第一个数, 跳过
            // 否则进入内层循环, 从num + 1开始找, 直到找不到
            if (!set.contains(num - 1)) {
                while (set.contains(++num)) {
                    len++;
                }
            }
            res = Math.max(res, len);
        }
        return res;
    }
}
