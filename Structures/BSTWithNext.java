package Structures;

public class BSTWithNext {
    public int val;
    public BSTWithNext left;
    public BSTWithNext right;
    public BSTWithNext next;

    public BSTWithNext() {}

    public BSTWithNext(int val) {
        this.val = val;
    }

    public BSTWithNext(int val, BSTWithNext left, BSTWithNext right, BSTWithNext next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}
