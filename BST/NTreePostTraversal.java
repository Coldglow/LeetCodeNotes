// https://leetcode.com/problems/n-ary-tree-postorder-traversal/
package BST;

import Structures.MultiNodeTree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NTreePostTraversal {
    // 后序递归
    public List<Integer> postOrderRec(MultiNodeTree root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        postRec(root, res);
        return res;
    }

    public void postRec(MultiNodeTree node, List<Integer> res) {
        if (node == null) {
            return;
        }
        for (MultiNodeTree next : node.children) {
            postRec(next, res);
        }
        res.add(node.val);
    }

    // 后序迭代
    // 两个栈
    public List<Integer> postOrderIterative(MultiNodeTree root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Stack<MultiNodeTree> inStack = new Stack<>();
        Stack<MultiNodeTree> outStack = new Stack<>();
        inStack.push(root);
        List<Integer> res = new ArrayList<>();
        while (!inStack.isEmpty()) {
            MultiNodeTree cur = inStack.pop();
            for (MultiNodeTree next : cur.children) {
                inStack.push(next);
            }
            outStack.push(cur);
        }
        while (!outStack.isEmpty()) {
            res.add(outStack.pop().val);
        }
        return res;
    }
}
