// https://leetcode.cn/problems/swap-nodes-in-pairs/
package List;

import Structures.ListNode;

public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 主要是要有一个虚拟节点来固定头节点位置  交换不难
        ListNode cur = head.next;
        ListNode pre = head;
        ListNode res = new ListNode();  // res作为虚拟节点
        res.next = head;
        ListNode pPre = res;
        while (true) {
            pre.next = cur.next;
            cur.next = pre;
            pPre.next = cur;
            // 交换完毕，向右移动
            if (pre.next != null && pre.next.next != null) {
                pPre = pre;
                cur =  pre.next.next;
                pre = pre.next;
            } else {
                break;
            }
        }
        return res.next;
    }
}
