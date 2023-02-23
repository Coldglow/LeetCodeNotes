// https://leetcode.com/problems/fruit-into-baskets/
package Array;

import java.util.Arrays;
import java.util.HashMap;

public class TotalFruit {
    // 错误  卡在了  1 0 1 4 1 4 1 2 3 上
    public int totalFruit(int[] fruits) {
        if (fruits.length <= 2) {
            return fruits.length;
        }
        // 找到第一个种类的数量
        int type1 = fruits[0];
        int num1 = 1;
        int index = 1;
        int type2 = -1;
        int num2 = 0;
        while (index < fruits.length && (fruits[index] == type1 || -1 == type2 || fruits[index] == type2)) {
            if (type2 == -1 && fruits[index] != type1) {
                type2 = fruits[index];
            }
            if (fruits[index] == type1) {
                num1++;
            } else {
                num2++;
            }
            index++;
        }
        int res = num2 + num1;
        // 此时index指向第三个种类的树
        for (int i = index; i < fruits.length; i++) {
            if (fruits[i] == type1) {
                num1++;
            } else if (fruits[i] == type2) {
                num2++;
            } else if (fruits[i - 1] == type1) {  // 新品种
                type2 = fruits[i];  // 这里 最开始的1会被算进去 所以最后出来是6，正确应该是5
                num2 = 1;
            } else {
                type1 = fruits[i];
                num1 = 1;
            }
            res = Math.max(res, num1 + num2);
            System.out.println("num1: " + num1 + "  num2: " + num2);
        }
        return res;
    }

    // 滑动窗口
    public int totalFruit2(int[] fruits) {
        if (fruits.length <= 2) {
            return fruits.length;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int L = 0, R = 0, res = 0;
        while (R < fruits.length) {
            map.put(fruits[R], map.getOrDefault(fruits[R], 0) + 1);
            R++;
            while (map.size() > 2) {
                map.put(fruits[L], map.get(fruits[L]) - 1);
                if (map.get(fruits[L]) == 0) {
                    map.remove(fruits[L]);
                }
                L++;
            }
            res = Math.max(res, R - L + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[] {3, 3, 3, 3};
        TotalFruit obj = new TotalFruit();
        int res = obj.totalFruit(arr1);
        System.out.println(res);
    }
}
