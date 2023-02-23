// https://leetcode.com/problems/linked-list-cycle-ii/
package List;

import Structures.ListNode;

public class DetectCycle {
    /**
     * 双指针，当第一轮相遇之后，fast回到头节点，当第二次相遇的时候就走到了环的起始节点
     * 第二轮fast走过的步数就是环开始节点的下标
     * @param head  head
     * @return jjj
     */
    public int detectCycle(ListNode head) {
        // 防止节点为空或者只有一个节点的情况
        if (head == null || head.next == null) {
            return -1;
        }
        ListNode slow = head;
        ListNode fast = head;
        int fastMove = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                break;
            }
        }
        if (slow != fast) {
            return -1;
        }
        // fast回到头节点，第二次相遇
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
            fastMove++;
        }
        return fastMove;
    }
}
