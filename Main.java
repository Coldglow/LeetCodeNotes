
import Structures.*;

import java.util.*;


public class Main {
    List<List<String>> res = new LinkedList<>();
    Deque<String> stack = new ArrayDeque<>();
    public List<List<String>> partition(String s) {
        partitionStr(s.toCharArray(), 0);
        return res;
    }

    public void partitionStr(char[] chars, int start) {
        if (start == chars.length) {
            res.add(new LinkedList<>(stack));
            return;
        }

        for (int i = start; i < chars.length; i++) {
            if (!isPalindrome(chars, start, i)) {
                continue;
            }
            stack.addLast(new String(chars, start, i - start + 1));
            partitionStr(chars, i + 1);
            stack.pollLast();
        }
    }

    public boolean isPalindrome(char[] chars, int start, int end) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public static void main(String[] args) {
        String s = "aab";
        Main o = new Main();
        List<List<String>> res = o.partition(s);
        for (List<String> list : res) {
            for (String str : list) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
//        ArrayList<Integer> a = new ArrayList<>(6);
//        a.add(0, 5);
//        a.add(0,4);
//        a.add(0, 3);
//        a.add(0,2);
//        a.add(0, 1);
//        a.add(0,0);
//        a.add(0,-1);
//        for (int i : a) {
//            System.out.println(i);
//        }

    }
}
