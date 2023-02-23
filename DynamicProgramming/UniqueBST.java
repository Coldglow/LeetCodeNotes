// https://leetcode.com/problems/unique-binary-search-trees/
package DynamicProgramming;
/**
 * 2023.2.22
 */
public class UniqueBST {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < n; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    /**
     * 卡塔兰数：H（n） = H（0） * H（n - 1） + H(1) * H(n - 2) + ... + H(n - 1) * H(0)，其中n >= 2
     * 简化成一阶递推关系：H(n + 1) = 2 (2 * n + 1) / (n + 2) * H(n)   H(0) = 1
     * @param n   n
     * @return  123
     */
    public int numTrees2(int n) {
        long C = 1;
        for(int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int)C;
    }
}
