package Structures;

import java.util.List;

public class MultiNodeTree {
    public int val;
    public List<MultiNodeTree> children;

    public MultiNodeTree() {}

    public MultiNodeTree(int val) {
        this.val = val;
    }

    public MultiNodeTree(int val, List<MultiNodeTree> children) {
        this.val = val;
        this.children = children;
    }
}
