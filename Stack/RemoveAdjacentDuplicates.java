//
package Stack;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveAdjacentDuplicates {
    // 先用队列实现
    public String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (deque.isEmpty() || c != deque.peekLast()) {
                deque.addLast(c);
            } else {
                deque.pollLast();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }


}
