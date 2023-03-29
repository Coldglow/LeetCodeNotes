// https://leetcode.cn/problems/maximal-network-rank/
package Greed;

/**
 * @author David Wong
 * @date 14/03/2023 21:12
 * Scanner in = new Scanner(System.in);
 */
public class MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] count = new int[n];
        int[][] map = new int[n][n];
        int res = 0;
        for (int[] road : roads) {
            count[road[0]]++;
            count[road[1]]++;
            map[road[0]][road[1]]++;
            map[road[1]][road[0]]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = map[i][j] == 1 ? count[i] + count[j] - 1 : count[i] + count[j];
                res = Math.max(res, temp);
            }
        }
        return res;
    }
}
