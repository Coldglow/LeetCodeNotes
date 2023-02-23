// https://leetcode.com/problems/queue-reconstruction-by-height/
package Greed;

import java.util.*;

public class QueueReconstructByHeight {
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ?  o2[0] - o1[0] : o1[1] - o2[1];
            }
        });
        List<int[]> res = new ArrayList<>();
        for (int[] person : people) {
            res.add(person[1], person);
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        int[][] arr = new int[6][2];
        arr[0] = new int[] {7,0};
        arr[1] = new int[] {4,4};
        arr[2] = new int[] {7,1};
        arr[3] = new int[] {5,0};
        arr[4] = new int[] {6,1};
        arr[5] = new int[] {5,2};
        reconstructQueue(arr);
    }
}
