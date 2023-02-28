package Youyong;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author David Wong
 * @date 25/02/2023 07:42
 */
public class IsMapped {
    public static boolean check(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
            map.put(s.charAt(i), t.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();
        System.out.println(check(s, t));
    }
}
