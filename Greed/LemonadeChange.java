// https://leetcode.com/problems/lemonade-change/
package Greed;

public class LemonadeChange {
    public static boolean lemonadeChange(int[] bills) {
        int cash5 = 0, cash10 = 0;
        for (int bill : bills) {
            if (bill == 5) {
                cash5++;
            } else if (bill == 10) {
                if (cash5 == 0) {
                    return false;
                }
                cash5--;
                cash10++;
            } else {
                if (cash10 > 0 && cash5 > 0) {
                    cash10--;
                    cash5 --;
                } else if (cash5 > 2) {
                    cash5 -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = new int[] {5,5,10,10,5,20,5,10,5,5};
        lemonadeChange(bills);
    }
}
