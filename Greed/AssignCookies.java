// https://leetcode.com/problems/assign-cookies/
package Greed;

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gi= 0, si = 0;
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si]) {
                res++;
                gi++;
            }
            si++;
        }
        return res;
    }
}
