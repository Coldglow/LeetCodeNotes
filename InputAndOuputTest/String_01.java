package InputAndOuputTest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 10:27
 */
public class String_01 {
    // 输入后排序
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = in.next();
        }
        Arrays.sort(str);
        for (int i = 0; i < n - 1; i++) {
            System.out.print(str[i] + " ");
        }
        System.out.print(str[n - 1]);
    }
}
