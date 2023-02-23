// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
package Greed;

import java.util.ArrayList;
import java.util.HashMap;

public class MinNumArrowsToBurstBalloons {
    public static int findMinArrowShots(int[][] points) {
        ArrayList<int[]> records = new ArrayList<>();
        records.add(points[0]);
        for (int[] point : points) {
            boolean flag = false;
            for (int[] record : records) {
                if (point[0] >= record[0] && point[1] <= record[1]) {  // 完全位于范围内
                    record[0] = point[0];
                    record[1] = point[1];
                    flag = true;
                } else if (point[1] >= record[0] && point[0] <= record[0]) {
                    // point位于record左侧，但有一部分相交，即point[1] >= record[0]
                    record[1] = point[1];
                    flag = true;
                } else if (point[0] <= record[1] && point[1] >= record[1]) {
                    // point位于record的右侧，即point[0] <= record[0]
                    record[0] = point[0];
                    flag = true;
                }
            }
            if (!flag) {
                records.add(point);
            }
        }
        return records.size();
    }

    public static void main(String[] args) {
        int[][] arr = new int[4][2];
        arr[0] = new int[] {10,16};
        arr[1] = new int[] {2,8};
        arr[2] = new int[] {1,6};
        arr[3] = new int[] {7,12};
        findMinArrowShots(arr);
//        arr[4] = new int[] {6,1};
//        arr[5] = new int[] {5,2};
    }
}
