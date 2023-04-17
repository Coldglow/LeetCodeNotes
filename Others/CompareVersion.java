package Others;

import java.util.Arrays;

/**
 * @author David Wong
 * @date 17/04/2023 21:53
 * Scanner in = new Scanner(System.in);
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int n = Math.min(s1.length, s2.length);
        int i = 0;
        while (i < n) {
            int n1 = Integer.parseInt(s1[i]);
            int n2 = Integer.parseInt(s2[i]);
            if (n1 < n2) {
                return -1;
            } else if (n1 > n2) {
                return 1;
            }
            i += 1;
        }

        if (i < s1.length) {
            for (; i < s1.length; i++) {
                if (Integer.parseInt(s1[i]) > 0) {
                    return 1;
                }
            }
        }

        if (i < s2.length) {
            for (; i < s2.length; i++) {
                if (Integer.parseInt(s2[i]) > 0) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersion o = new CompareVersion();
        int res = o.compareVersion("0.1", "1.1");
        System.out.println(res);
    }
}
