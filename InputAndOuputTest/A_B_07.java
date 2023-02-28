package InputAndOuputTest;

import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 10:19
 */
public class A_B_07 {
    // 输入的行数不确定
    // 这种每行不确定的只能用nextLine然后用split分割
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int sum = 0;
            // 这里必须是字符串数组 不能是char数组
            String[] s = in.nextLine().split(" ");
            for (String str : s) {
                sum += Integer.parseInt(str);
            }
            System.out.println(sum);
        }
    }
}
