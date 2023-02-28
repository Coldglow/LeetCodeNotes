package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 09:40
 */
public class A_B_04 {
    // 输入多行
    // 每行第一个数表示这行输入多少个数
    // 每行输入结束后输入出该行的输入的数的和
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n != 0) {
            int sum = 0;
            while (n-- != 0) {
                sum += in.nextInt();
            }
            System.out.println(sum);
            n = in.nextInt();
        }
    }
}
