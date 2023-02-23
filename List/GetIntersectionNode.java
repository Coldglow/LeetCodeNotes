// https://leetcode.cn/problems/intersection-of-two-linked-lists/
package List;

import Structures.ListNode;

public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        int len1 = 0;
        int len2 = 0;
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != null) {
            node1 = node1.next;
            len1++;
        }
        while (node2 != null) {
            node2 = node2.next;
            len2++;
        }
        ListNode longer = len1 > len2 ? headA : headB;
        ListNode shorter = longer == headA ? headB : headA;
        int moreLen = Math.abs(len1 - len2);
        while (moreLen != 0) {
            longer = longer.next;
            moreLen--;
        }

        while (longer != null && shorter != null && longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return shorter;
    }


    // 2023.2.19  一次过
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode curA = headA, curB = headB;
        int lenA = 0, lenB = 0;
        while (curA != null) {
            curA = curA.next;
            lenA++;
        }
        while (curB != null) {
            curB = curB.next;
            lenB++;
        }
        // 比较lenA和lenB的长度
        // 让curA指向较长链表的头节点
        // curB指向较短链表的头节点
        curA = lenA > lenB ? headA : headB;
        curB = curA == headA ? headB : headA;
        // 长链表先走多出来一截
        int diff = Math.abs(lenA - lenB);
        while (diff-- > 0) {
            curA = curA.next;
        }
        // 然后一起走
        while (curA != curB && curA != null && curB != null) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA == curB && curA != null ? curA : null;
    }
}
