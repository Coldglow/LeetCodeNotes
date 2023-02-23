// https://leetcode.com/problems/n-ary-tree-preorder-traversal/
package BST;

import Structures.MultiNodeTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NTreePreTraversal {

    public List<Integer> preOrderRec(MultiNodeTree root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        preRec(root, res);
        return res;
    }

    public void preRec(MultiNodeTree node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        for (MultiNodeTree next : node.children) {
            preRec(next, res);
        }
    }

    // 迭代
    public List<Integer> preOrderIterative(MultiNodeTree root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Stack<MultiNodeTree> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            MultiNodeTree cur = stack.pop();
            for (int i = cur.children.size() - 1; i > -1; i--) {
                stack.push(cur.children.get(i));
            }
            res.add(cur.val);
        }
        return res;
    }
}
