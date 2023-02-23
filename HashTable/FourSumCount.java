// https://leetcode.cn/problems/4sum-ii/
package HashTable;

import java.util.HashMap;

public class FourSumCount {
    // 分治策略，先求出两个数组所有的和，记录在哈希表中，然后遍历哈希表
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int res = 0;
        // key是两数之和 value是这个和出现的次数
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for (int k1 : nums1) {
            for (int k2 : nums2) {
                map1.put(k1 + k2, map1.getOrDefault(k1 + k2, 0) + 1);
            }
        }
        for (int k3 : nums3) {
            for (int k4 : nums4) {
                map2.put(k3 + k4, map2.getOrDefault(k3 + k4, 0) + 1);
            }
        }
        for (int val : map1.keySet()) {
//            System.out.println("val:" + val + "  num:" + map1.get(val));
            if (map2.containsKey(-val)) {
                res += (map1.get(val) * map2.get(-val));
//                System.out.println("val:" + val + "  num:" + map1.get(val));
//                System.out.println("-val:" + val + "  num:" + map2.get(val));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] {1,2};
        int[] arr2 = new int[] {-2,-1};
        int[] arr3 = new int[] {-1,2};
        int[] arr4 = new int[] {0,2};
        FourSumCount o = new FourSumCount();
        o.fourSumCount(arr1, arr2, arr3, arr4);
    }

}
