package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 09:31
 */
public class A_B_03 {
    // 每行输入两个数 空格分开
    // 输入两个数都是0的时候退出
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            if (a == 0 && b == 0){
                break;
            }
            System.out.println(a + b);
        }
    }
}
