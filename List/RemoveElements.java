// https://leetcode.com/problems/remove-linked-list-elements/
package List;

import Structures.ListNode;

public class RemoveElements {
    public ListNode removeElement(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        // 设置一个虚拟头节点 更好操作
        ListNode curH = new ListNode();
        curH.next = head;
        ListNode cur = head;
        ListNode pre = curH;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = pre.next;
            }
            cur = cur.next;
        }

        return curH.next;
    }
}
