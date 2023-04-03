package List;

import Structures.ListNode;

/**
 * @author David Wong
 * @date 03/04/2023 09:44
 * Scanner in = new Scanner(System.in);
 */
public class SortList {
    /*
        暴力，O(n^2)
        自底向上归并:
            从两个长度为1的链表开始合并
            然后两个长度为2的链表合并，因为长度为1的时候已经排好序了，所以此时相当于合并两个有序链表
            然后长度为4，以此类推，直到长度超过链表长度
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 计算列表长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            length++;
        }
        // 设置虚拟头节点
        ListNode dummy = new ListNode();
        dummy.next = head;
        // 迭代遍历，每次子链表长度变长一倍
        for (int subLength = 1; subLength < length; subLength = subLength << 1) {
            ListNode prev = dummy, cur = dummy.next;
            while (cur != null) {
                // 找到两个列表的头节点，并合并
                ListNode head1 = cur;
                for (int i = 1; i < subLength && cur.next != null; i++) {
                    cur = cur.next;
                }
                // 找到第二个子列表的头节点
                ListNode head2 = cur.next;
                // 截断第一个子列表
                cur.next = null;
                cur = head2;
                // 找到第二个子列表的尾节点并截断
                for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode next = null;
                // 如果cur != null 说明第二个子链表的长度满足等于subLength这个条件
                // 如果cur == null 说明第二个子链表长度不够，无需再截断，因为其末尾本身就是null
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                // 合并两个链表并返回头节点
                prev.next = merge(head1, head2);
                // 合并下一对子链表，因此prev节点需要移动到最后
                while (prev.next != null) {
                    prev = prev.next;
                }
                cur = next;
            }
        }
        return dummy.next;
    }

    // 合并两个有序列表
    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }

        return dummy.next;
    }
}
