// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
package List;

import Structures.ListNode;

public class RemoveNthFromEnd {
    /**
     * 先通过快慢指针求出链表长度  然后根据长度计算出要删除的节点在前半部分还是后半部分
     * 最后遍历就行
     * 麻烦在于边界情况的整理
     * @param head   head
     * @param n  b
     * @return  d
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        // 排除只有一个节点的情况
        if (head.next == null) {
            if (n == 1) {
                return null;
            } else {
                return head;
            }
        }

        int sPass = 1;
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode res;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            sPass++;
        }
        // 求出链表长度
        int listLen = fast != null ? 2 * sPass : 2 * sPass - 1;

        if (listLen - n >= sPass) {
            // 如果n位于链表后半部分 slow继续往后走
            res = delNthEndNode(head, slow, listLen - n, sPass);
        } else {
            // 否则就重新从头节点开始遍历
            slow = head;
            res = delNthEndNode(head, slow, listLen - n, 1);
        }
        return res;
    }

    public ListNode delNthEndNode(ListNode head, ListNode start, int n, int begin) {
        while (begin < n) {
            start = start.next;
            begin++;
        }
        if (n == 0) {  // 如果要删除第一个节点  直接返回头节点的下一个
            return head.next;
        } else {  // 否则就删除start节点的下一个
            start.next = start.next == null ? null : start.next.next;
            return head;
        }
    }

    /**
     * 也是双指针，不过快指针比慢指针快n就行，这样当快到达尾部的时候，慢就是要删除的
     * 我是傻逼
     * 操作链表的时候一定要看用不用虚拟节点
     * 操作链表的时候一定要看用不用虚拟节点
     * 操作链表的时候一定要看用不用虚拟节点
     * 操作链表的时候一定要看用不用虚拟节点
     * 操作链表的时候一定要看用不用虚拟节点
     * @param head head
     * @param n n
     * @return d
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    // 也是双指针，不过没有用虚拟节点
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && n > -1) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (n > -1) {
            return head.next;
        } else {
            slow.next = slow.next.next;
            return head;
        }
    }

}
