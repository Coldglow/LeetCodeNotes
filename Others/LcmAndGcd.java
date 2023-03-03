package Others;

/**
 * @author David Wong
 * @date 28/02/2023 13:26
 * Scanner in = new Scanner(System.in);
 */
public class LcmAndGcd {
    // 辗转相除法得到a和b的最大公约数
    public static int gcd(int a, int b) {
        // 如果a < b  则交换
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

    // 求两个数的最小公倍数  a * b / a和b的最大公约数
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println(lcm(3, 4));
    }
}
