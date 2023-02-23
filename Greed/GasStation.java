// https://leetcode.com/problems/gas-station/
package Greed;

public class GasStation {
    /**
     * 从头开始走，如果最后剩余的汽油量小于0，那么可以确定从任何一个位置走都不可能走完全程
     * 否则则存在一个位置，可以走完全程，可能存在多个答案
     * 但剩余汽油量最少的位置的下一个位置一定是答案
     * 折线图的最低位置
     * @param gas  gas
     * @param cost cost
     * @return i
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int minGas = Integer.MAX_VALUE;
        int minIndex = 0;
        int curGas = 0;
        for (int i = 0; i < gas.length; ++i) {
            curGas = curGas + gas[i] - cost[i];
            if (minGas > curGas) {
                minGas = curGas;
                minIndex = i;
            }
        }
        return curGas < 0 ? -1 : (minIndex + 1) % gas.length;
    }

    public static void main(String[] args) {
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};
        GasStation o = new GasStation();
        System.out.print(o.canCompleteCircuit(gas, cost));
    }
}
