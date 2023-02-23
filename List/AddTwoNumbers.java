// https://leetcode.cn/problems/add-two-numbers/
package List;

import Structures.ListNode;

public class AddTwoNumbers {
    /**
     * 没有算法，逻辑理清就行
     * @param l1 1
     * @param l2 2
     * @return 2
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0 && l1.next == null) {
            return l2;
        }
        if (l2.val == 0 && l2.next == null) {
            return l1;
        }
        ListNode res= new ListNode();
        ListNode cur = res;
        int geWei, val1, val2;
        int shiWei = 0;
        while (l1 != null || l2 != null || shiWei == 1) {
            val1 = l1 == null ? 0 : l1.val;
            val2 = l2 == null ? 0: l2.val;
            geWei = (val1 + val2 + shiWei) % 10;
            shiWei = (val1 + val2 + shiWei) / 10;
            cur.val = geWei;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if (l1 != null || l2 != null || shiWei == 1) {
                cur.next = new ListNode();
                cur = cur.next;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] l1 = new int[] {0,8,6,5,6,8,3,5,7};
        int[] l2 = new int[] {6,7,8,0,8,5,8,9,7};
        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        for (int i = 0; i < l1.length; i++) {
            cur1.val = l1[i];
            cur2.val = l2[i];
            if (i + 1 == l1.length) {
                break;
            }
            cur1.next = new ListNode();
            cur2.next = new ListNode();
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        cur1 = head1;
        cur2 = head2;
        while (cur1 != null) {
            System.out.print(cur1.val + " ");
            cur1 = cur1.next;
        }
        System.out.println();
        while (cur2 != null) {
            System.out.print(cur2.val + " ");
            cur2 = cur2.next;
        }

        AddTwoNumbers obj = new AddTwoNumbers();
        obj.addTwoNumbers(head1, head2);
    }
}
