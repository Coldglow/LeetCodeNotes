// https://leetcode.cn/problems/reverse-linked-list-ii/
package List;

import Structures.ListNode;

/**
 * 一、 2023.2.19
 */

public class ReverseList02 {
    // 就是翻转k个链表的一部分
    // 找到左右两个边界，翻转
    // 需要遍历两次[left, right]区域
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        int index = 1;
        ListNode leftHead = new ListNode();
        leftHead.next = head;
        ListNode rightTail = null;

        while (index < left) {
            leftHead = leftHead.next;
            index++;
        }
        // 此时leftHead指向left的前一个节点
        rightTail = leftHead.next;
        while (index < right) {
            rightTail = rightTail.next;
            index++;
        }
        // tailNext表示要反转的最后一个节点的下一个节点
        ListNode tailNext = rightTail.next;
        ListNode reversedHead = reverse(leftHead.next, tailNext);
        // 连接回原链表
        leftHead.next.next = tailNext;
        leftHead.next = reversedHead;

        return left == 1 ? leftHead.next : head;
    }

    // 注意这里需要传入不需要翻转的两个边界
    public static ListNode reverse(ListNode head, ListNode tail) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp;
        while (cur != tail) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 一边遍历一边翻转，只需要遍历一次【left，right】区域
    public static ListNode reverseBetween02(ListNode head, int left, int right) {
        int index = 1;
        ListNode leftHead = new ListNode();
        leftHead.next = head;
        ListNode cur = head;
        // 同样，leftHead指向左边界
        while (index < left) {
            leftHead = leftHead.next;
            cur = cur.next;
            index++;
        }
        ListNode pre = null, next;
        // 此时cur指向左边第一个需要翻转的节点
        while (index < right + 1) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            index++;
        }
        // 此时pre指向翻转后的那一段的头节点
        // cur指向原链表右边界
        leftHead.next.next = cur;
        leftHead.next = pre;

        return left == 1 ? leftHead.next : head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode res = reverseBetween(node1, 2, 4);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
