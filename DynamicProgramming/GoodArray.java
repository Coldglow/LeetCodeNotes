/**
 * 定义一个数组是好数组，当且仅当每个元素都不等于它前面的所有元素之和。
 * 例如[1,4,4]是好数组，而[2,1,3]不是好数组 (因为2+1=3)。当然[1, 1, 2]也不是，
 * 因为强调每个元素都不等于它前面的所有元素之和。
 * 小红想知道，长度为n的数组，且每个元素都在[1,m]之间，有多少个不同的好数组?
 */
package DynamicProgramming;

/**
 * @author David Wong
 * @date 24/02/2023 21:14
 */
public class GoodArray {
    // 对于确定的m来说，每增加一个位置，都会多出m种可能，所以全排列是m^n
    public int goodArray(int m, int n) {
        return (int)(Math.pow(m, n) - Math.pow(m, (n - 1)));
    }
}
