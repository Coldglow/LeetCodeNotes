package HengSheng;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author David Wong
 * @date 23/03/2023 10:52
 * Scanner in = new Scanner(System.in);
 */
public class DetectSigns {

    public static void detect(String inputs) {
        Scanner in = new Scanner(System.in);
        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '红');
        map.put('(', '蓝');
        map.put('[', '黄');
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');

        char[] chs = inputs.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (char c : chs) {
            // 左侧
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                sb.append(map.get(c));
            } else if (map.get(c) != stack.peek()){   // 右侧
                System.out.println("输入错误");
                return;
            } else {
                sb.append(map.get(stack.pop()));
            }
        }
        System.out.println(sb);
    }


    public static void main(String[] args) {
        String s = "{[()]}{}[]";
        detect(s);
    }
}
