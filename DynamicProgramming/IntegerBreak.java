// https://leetcode.com/problems/integer-break/
package DynamicProgramming;
/**
 * 2023.2.22
 */
public class IntegerBreak {
    // i < 10:
    // 尽量分成3和2相乘
    public int integerBreak(int n) {
        if (n < 4) {
            return n - 1;
        }
        if (n % 3 == 0) {
            return (int)(Math.pow(3, n / 3));
        } else if (n % 3 == 2) {
            return (int)(Math.pow(3, n / 3) * 2);
        } else {
            return (int)(Math.pow(3, (n / 3) - 1) * 4);
        }
    }
}
