// https://leetcode.cn/problems/linked-list-cycle-ii/
package List;

import Structures.ListNode;

/**
 * 2023.2.20
 */
public class LinkedListCircle02 {
    // 返回进入链表的第一个节点
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return null;
        }
        fast = head;
        // 相遇之后fast回到起点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
