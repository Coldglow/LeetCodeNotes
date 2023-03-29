
import Structures.*;

import java.util.*;


public class Main {

    public int[] printNum() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int k = s.nextInt();
        if ((m - 1) * (n - 1) < k) {
            return null;
        }
        int[] res = new int[n];
        Arrays.fill(res, 1);
        if (m - 1 > k) {
            res[0] = k + 1;
            return res;
        }
        res[0] = m;
        k -= m - 1;
        for (int i = 2; i < n; i += 2) {
            if (k - (m - 1) * 2 <= 0) {
                break;
            }
            res[i] = m;
        }

        if (k > 0) {
            res[n - 1] += k;
        }
        System.out.println(k);
        return res;
    }

    public boolean isPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }

        String s = "   123   ";

        System.out.println(s.trim());
//        String s = "aab";
//        Main o = new Main();
//        int[] res = o.printNum();
//        for (int n : res) {
//            System.out.print(n + " ");
//        }
//        ArrayList<Integer> a = new ArrayList<>(6);
//        a.add(0, 5);
//        a.add(0,4);
//        a.add(0, 3);
//        a.add(0,2);
//        a.add(0, 1);
//        a.add(0,0);
//        a.add(0,-1);
//        for (int i : a) {
//            System.out.println(i);
//        }

    }
}
