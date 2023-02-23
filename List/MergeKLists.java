//  https://leetcode.cn/problems/merge-k-sorted-lists/
package List;

import Structures.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一、 2023.2.19
 */

public class MergeKLists {
    /**
     * 暴力解法
     * 一条链一条链的合并，每次合并完一条链都回到头节点遍历
     * @param lists  ...
     * @return  ...
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // 固定一个虚拟的头节点
        ListNode head = new ListNode(Integer.MIN_VALUE);
        // 每次遍历用的节点
        ListNode cur = head;
        // 记录结果链表的下一个节点  输入链表的下一个节点
        ListNode curNext = null, nodeNext = null;
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            // 每合并一个新的链表，结果链表都先走到第一个不重复的值
            // 即 如果结果链表是 1 - 1 - 1 - 2 - 4，先走到最后一个1
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // 开始连接
            while (node != null) {
                // 如果结果链表里cur指向的值小于输入链表里node指向的值
                // 结果链表的节点cur后移
                while (cur.next != null && cur.next.val <= node.val) {
                    cur = cur.next;
                }
                // 如果cur已经是最后一个节点了，说明结果链表所有的值都比
                // 输入链表的第一个值小，输入链表直接挂到结果链表之后
                // 然后直接跳出连接下一个
                if (cur.next == null) {
                    cur.next = node;
                    break;
                }
                // 下面是合并操作
                curNext = cur.next;
                nodeNext = node.next;
                cur.next = node;
                node.next = curNext;
                node = nodeNext;
                cur = cur.next;
            }
            // 合并完成后，cur节点回到结果链表的头节点，合并下一个链表
            cur = head;
        }
        return head.next;
    }

    // 维护一个最小堆，每次从堆中取出最小值加到结果上
    public static ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head, node;
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // 初始化堆
        for (ListNode n : lists) {
            if (n != null) {
                heap.add(n);
            }
        }
        // 添加
        while (!heap.isEmpty()) {
            node = heap.poll();
            cur.next = node;
            if (node.next != null) {
                heap.add(node.next);
            }
            cur = cur.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode[] arr = new ListNode[3];
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(5);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(3);
        node2.next.next = new ListNode(4);

        ListNode node3 = new ListNode(2);
        node3.next = new ListNode(6);

        arr[0] = node1;
        arr[1] = node2;
        arr[2] = node3;
        ListNode[] arr2 = new ListNode[2];
        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(0);
        arr2[0] = node21;
        arr2[1] = node22;
        ListNode res = mergeKLists(arr2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
