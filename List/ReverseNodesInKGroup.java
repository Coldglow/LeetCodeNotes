// https://leetcode.com/problems/reverse-nodes-in-k-group/
package List;

import Structures.ListNode;

public class ReverseNodesInKGroup {
    /**
     * 可以直接用栈，也可以不用申请额外空间
     * res表示虚拟节点
     * 固定两个节点slow和fast  然后翻转 [slow.next, fast) 之间的节点
     * 然后slow连接翻转后的头 翻转后的尾连接fast
     * 然后移动slow到翻转后的尾
     * 循环
     * @param head  head
     * @param k  k
     * @return ...
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode res = new ListNode();
        res.next = head;
        ListNode slow = res, fast = head;
        while (fast != null) {
            int step = 0;
            // fast先走k步
            while (fast != null && step < k) {
                fast = fast.next;
                step++;
            }
            // 不能用fast == null 判断
            if (step < k) {
                break;
            }
            // 然后翻转slow和fast之间的节点
            ListNode tail = reverse(slow.next, fast);
            ListNode curHead = slow.next;
            curHead.next = fast;
            slow.next = tail;
            slow = curHead;
        }
        return res.next;
    }

    public ListNode reverse(ListNode head, ListNode tail) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        while (cur != tail) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
