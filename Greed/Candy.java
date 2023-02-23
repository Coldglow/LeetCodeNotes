// https://leetcode.com/problems/candy/
package Greed;

public class Candy {
    /**
     * 从左到右  应该是比前面的  而不应该是比后面的
     * 从右到左  应该是比后面的  而不是比前面的
     * @param ratings  rating
     * @return  f
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] right = new int[n];
        int[] left = new int[n];
        int res = 0;
        // 需要单独将right[0]和left[n-1]置为1
        right[0] = 1;
        left[n - 1] = 1;
        // 右规则，从1开始，比较[i]和[i - 1]的大小
        for (int i = 1; i < n; ++i) {
            right[i] = ratings[i] > ratings[i - 1] ? right[i - 1] + 1 : 1;
        }
        // 左规则，从n - 2开始，比较[i]和 [i + 1]的大小
        for (int i = n - 2; i > -1; --i) {
            left[i] = ratings[i] > ratings[i + 1] ? left[i + 1] + 1 : 1;
            res += Math.max(left[i], right[i]);
        }
        return res + Math.max(left[n -1], right[n -1]);
    }

    /**
        inc 表示最近的递增序列的长度   dec表示当前递减序列的长度  pre表示前一个同学分配的糖果数量
        如果是降序的情况，当前降序序列的长度就表示最高的糖果数量，相当于先加上小的  再加上大的
     */
    public static int candy2(int[] ratings) {
        int n = ratings.length;
        int res = 1;  // 假如只有一个孩子
        int pre = 1, inc = 1, dec = 0;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                // 处于升序序列
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                res += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {   // 如果当前降序序列和最近的升序序列相等，那么降序序列最高的那个要加1
                    dec++;
                }
                res += dec;
                pre = 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 4, 3, 2, 1};
        System.out.print(candy2(arr));
    }
}
