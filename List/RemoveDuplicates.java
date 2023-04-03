package List;
import Structures.ListNode;

/**
 * @author David Wong
 * @date 03/04/2023 09:32
 * Scanner in = new Scanner(System.in);
 */
public class RemoveDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur != null) {
            int count = 0;
            while (cur.next != null) {
                if (cur.val != cur.next.val) {
                    break;
                }
                count++;
                cur = cur.next;
            }
            if (count == 0) {
                pre.next = cur;
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
