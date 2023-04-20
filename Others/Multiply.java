package Others;

import java.util.LinkedList;

/**
 * @author David Wong
 * @date 18/04/2023 20:33
 * Scanner in = new Scanner(System.in);
 */
public class Multiply {
    /*
    两个数M和N相乘,结果的最大长度是len(M) + len(N)
    num1[i] * num2[j]
    结果的个位数应该位于arr[i + j + 1], 十位数位于arr[i+ j]的位置
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int n1 = num1.length(), n2 = num2.length();
        int[] arr = new int[n1 + n2];
        for (int i = n1 - 1; i > -1; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = n2 - 1; j > -1; j--) {
                int b = num2.charAt(j) - '0';
                int temp = a * b;
                arr[i + j + 1] += (temp % 10);
                if (arr[i + j + 1] > 9) {
                    arr[i + j] += (arr[i + j + 1] / 10);
                    arr[i + j + 1] %= 10;
                }
                arr[i + j] += (temp / 10);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            if (sb.length() == 0 && j == 0) {
                continue;
            }
            sb.append(j);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Multiply o = new Multiply();
        String res = o.multiply("123", "456");
        System.out.println(res);
    }
}
