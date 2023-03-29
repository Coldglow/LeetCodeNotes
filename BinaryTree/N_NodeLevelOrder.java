// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
package BinaryTree;

import Structures.MultiNodeTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class N_NodeLevelOrder {
    // 多节点树的层次遍历
    public List<List<Integer>> levelOrder(MultiNodeTree root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<MultiNodeTree> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        deque.addLast(root);
        MultiNodeTree cur;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                cur = deque.pollFirst();
                for (MultiNodeTree child : cur.children) {
                    deque.addLast(child);
                }
                level.add(cur.val);
            }
            res.add(level);
        }
        return res;
    }
}
