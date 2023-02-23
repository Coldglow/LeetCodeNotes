package List; /**
 * 合并两个有序链表
 */

import Structures.ListNode;

public class MergeTwoLists {
    /**
     * 自己申请一个虚拟头节点，哪个小往后加
     * 大于等于执行的步骤一样，只需关注小于就行
     * @param list1 头节点1
     * @param list2 头节点2
     * @return 合并后的头节点
     */
    public static ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }

        ListNode head = new ListNode();
        ListNode prev = head;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return head.next;
    }
}
