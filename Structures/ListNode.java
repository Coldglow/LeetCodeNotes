package Structures;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {};

    public ListNode(int value) {
        this.val = value;
    }
    public ListNode(int val, ListNode node) {
        this.val = val;
        this.next = node;
    }
}
