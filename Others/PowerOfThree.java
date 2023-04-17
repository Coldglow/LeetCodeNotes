package Others;

/**
 * @author David Wong
 * @date 16/04/2023 09:27
 * Scanner in = new Scanner(System.in);
 */
public class PowerOfThree {
    private int[] arr = new int[15];

    public boolean getPower(int n, int i, int count) {
        if (count == n) {
            return true;
        }
        if (i == -1) {
            return false;
        }
        return getPower(n, i - 1, count + arr[i]) || getPower(n, i - 1, count);
    }

    public boolean checkPowersOfThree(int n) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)Math.pow(3, i);
            if (arr[i] <= n) {
                index = i;
            }
        }
        if (arr[index] == n) {
            return true;
        }
        return getPower(n, index, 0);
    }
}
