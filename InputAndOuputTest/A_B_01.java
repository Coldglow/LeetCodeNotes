package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 09:20
 */
public class A_B_01 {
    public static void main(String[] args) {
        // 每一行输入两个数字  下一行自动输出和
        // 然后再输入两个。。。
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
