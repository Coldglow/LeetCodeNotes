// https://leetcode.cn/problems/reorder-list/
package List;

import Structures.ListNode;

public class ReorderList {
    /**
     * 下面用了数组来存储后半段节点
     * 其实也可以先通过快慢指针找到中点
     * 然后逆序后半段
     * 因为逆序函数可以返回逆序后的头节点
     * 这样就有了两个头节点，直接合并就行
     * @param head head
     */
    public void reorderList(ListNode head) {
        // 只有一个节点或者两个节点，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head, fast = head;
        // 记录slow左侧还剩几个节点
        int step = 0;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            step++;
        }
        // 节点奇数：slow指向中间的节点   节点偶数：slow指向下半部的第一个节点
        // 如果是偶数个节点，那么剩余的节点数就是step-1
        step = fast == null ? step - 1 : step;
        // 存储右边要改变位置的节点，同时断开连接
        ListNode cursor = slow.next;
        slow.next = null;
        ListNode[] rightNodes  = new ListNode[step];
        while (cursor != null) {
            rightNodes[--step] = cursor;
            cursor = cursor.next;
        }
        // 连接到左半部分
        cursor = head;
        for (ListNode node : rightNodes) {
            node.next = cursor.next;
            cursor.next = node;
            cursor = node.next;
        }
    }
}
