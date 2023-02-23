// https://leetcode.cn/problems/linked-list-cycle/
package List;

import Structures.ListNode;

public class LinkedListCircle {
    // 判断链表有无环
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
