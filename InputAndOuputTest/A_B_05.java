package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 09:49
 */
public class A_B_05 {
    // 输入n行
    // 每行第一个数表示该行输入多少数
    // 输出每行的和
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            int sum = 0;
            int m = in.nextInt();
            while (m-- != 0) {
                sum += in.nextInt();
            }
            System.out.println(sum);
            n--;
        }
    }
}
