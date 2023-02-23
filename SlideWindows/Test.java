package SlideWindows;

import Structures.ListNode;

import java.util.Arrays;

public class Test {

    public ListNode removeNthFromEnd(ListNode head, int n) {
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


    public static void main(String[] args) {
        int[] arr = new int[] {3,2,2,3, 4, 5, 3, 7, 10, 2, 6};
        System.out.println(Arrays.toString(arr));
        int a = 3;
        String s = "  ";
        System.out.println();
    }

}
