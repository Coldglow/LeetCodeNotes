package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 09:24
 */
public class A_B_02 {
    // 先输入一个整数n
    // 然后输入n行，每行有两个整数，空格分开
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (in.hasNext() && n-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
