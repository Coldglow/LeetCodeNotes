// https://leetcode.cn/problems/design-linked-list/
// 设计一个链表
package List;

public class MyLinkedList {

    static class LinkedList {
        private int val;
        private LinkedList next;
        private LinkedList prev;

        LinkedList() {};

        LinkedList(int val) {
            this.val = val;
            this.next = new LinkedList();
            this.prev = new LinkedList();
        }
    }

    private LinkedList head;
    private LinkedList tail;
    private int listLen;

    public MyLinkedList() {
        this.head = new LinkedList();
        this.tail = new LinkedList();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.listLen = 0;
    }

    public int get(int index) {
        if (index > -1 && index < this.listLen) {
            return -1;
        }

        return getIndex(index).val;
    }

    public void addAtHead(int val) {
        LinkedList newNode = new LinkedList(val);
        addBefore(this.head.next, newNode);
    }

    public void addAtTail(int val) {
        LinkedList newNode = new LinkedList(val);
        addBefore(this.tail, newNode);
    }

    public void addAtIndex(int index, int val) {
        if (index == this.listLen) {
            addAtTail(val);
        } else if (index > this.listLen) {
            return;
        } else if (index <= 0) {
            addAtHead(val);
        } else {
            LinkedList node = getIndex(index);
            LinkedList newNode = new LinkedList(val);
            addBefore(node, newNode);
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index <= this.listLen) {
            LinkedList delNode = getIndex(index);
            delNode.prev.next = delNode.next;
            delNode.next.prev = delNode.prev;
            this.listLen--;
        }
    }

    public void addBefore(LinkedList node, LinkedList target) {
        target.next = node;
        target.prev = node.prev;
        node.prev.next = target;
        node.prev = target;
        this.listLen++;
    }

    public LinkedList getIndex(int index) {
        LinkedList cur;
        int curIndex;
        if (this.listLen > index * 2) {
            cur = this.tail;
            curIndex = this.listLen - 1;
            while (curIndex != index) {
                cur = cur.prev;
                curIndex--;
            }
        } else {
            cur = this.head;
            curIndex = 0;
            while (curIndex != index) {
                cur = cur.next;
                curIndex++;
            }
        }
        return cur;
    }
}
