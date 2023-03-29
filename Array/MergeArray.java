// https://leetcode.cn/problems/merge-intervals/
package Array;

import java.util.*;

public class MergeArray {
    // 单调栈不可行，因为无序
    public int[][] merge(int[][] intervals) {
        char cha97 = 97;
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 先按照0位置从小到大排序，那么可以合并的区间一定是连续的
        // 然后只需要逐个比较右端点即可
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Deque<int[]> res = new LinkedList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            // 如果当前区间的左端点在res最后一个区间的右端点之后，那么不会重合，直接添加
            if (res.isEmpty() || l > res.peekLast()[1]) {
                res.add(new int[] {l, r});
            } else {
                // 否则就重合，需要比较更新右端点的值
                res.peekLast()[1] = Math.max(r, res.peekLast()[1]);
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    /*
        2023.03.29
        先按照数组的start排序
     */
    public int[][] merge_2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> res = new LinkedList<>();
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // 如果cur数组的开头小于等于prev数组的结尾，那么合并
            if (intervals[i][0] > prev[1]) {
                res.add(new int[] {prev[0], prev[1]});
                prev = intervals[i];
            } else {
                prev[1] = Math.max(intervals[i][1], prev[1]);
            }
        }
        res.add(new int[] {prev[0], prev[1]});

        return res.toArray(new int[res.size()][]);
    }
}
