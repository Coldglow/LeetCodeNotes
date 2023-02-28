package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 10:17
 */
public class A_B_06 {
    // 每行输入第一个数表示该行输入多少个数 输出每行的和
    // 没定义输入多少行
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int sum = 0;
            while (n-- != 0) {
                sum += in.nextInt();
            }
            System.out.println(sum);
        }
    }
}
