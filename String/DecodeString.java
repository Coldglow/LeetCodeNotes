// https://leetcode.cn/problems/decode-string/
package String;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author David Wong
 * @date 20/06/2023 12:18
 * Scanner in = new Scanner(System.in);
 */
public class DecodeString {
    /**
     * 其实也可以不用记录level, 将有关level的内容都去掉也可以AC
     */
    private Deque<Integer> nums = new LinkedList<>();   // 记录该层要循环几次
    private int level = 0;   // 表示这是第几层
    private StringBuilder res = new StringBuilder();  // 最终的结果
    private int index = 0;   // 记录此时ch的下标

    public String decodeString(String s) {
        char[] ch = s.toCharArray();
        // 主方法的while永远执行最外层的添加
        while (index < ch.length) {
            // 先获取数字
            if (Character.isDigit(ch[index])) {
                nums.addLast(getNumber(ch));  // 记录这一层要循环的次数
            } else if (ch[index] == '[') {  // 开始循环添加字符串
                index++;  // 这里++是为了跳过 '[' 这个字符, 这样在执行record方法
                level++;
                res.append(record(ch));
            } else if (level == 0){
                res.append(ch[index]);    // 如果上述条件都不成立, 并且处于最外层, 直接添加
            }
            index++;
        }
        return res.toString();
    }

    // 获取该层要循环的次数
    public int getNumber(char[] ch) {
        int n = 0;
        while (Character.isDigit(ch[index])) {
            n = n * 10 + ch[index] - '0';
            index++;
        }
        // 让调用者自己执行index++
        index--;
        return n;
    }

    /**
     * 开始记录, 当 ch[i] == [  的时候执行
     */
    public String record(char[] ch) {
        StringBuilder sb = new StringBuilder();    // 记录暂时循环的结果
        // 循环直到碰到 ']'
        while (ch[index] != ']') {
            // 如果碰到 [ 说明要进入下一层
            if (ch[index] == '[') {
                index++;
                level++;
                sb.append(record(ch));  // 添加内层的字符串
            } else if (Character.isDigit(ch[index])){
                nums.addLast(getNumber(ch));
            } else {
                sb.append(ch[index]);
            }
            index++;
        }
        // 直接在这里添加
        int n = nums.pollLast();  // 弹出末端的重复次数
        level--;     // 层级减少
        // 重复sb重复n次
        return sb + String.valueOf(sb).repeat(Math.max(0, n - 1));
    }

    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        String s = decodeString.decodeString("abc3[cd]xyz");
        System.out.println(s);
    }
}
