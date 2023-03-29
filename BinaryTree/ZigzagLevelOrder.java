// https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import Structures.TreeNode;

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        boolean leftToRight = true;
        // 左向右：取头，先入左再入右，入尾部
        // 右向左：取尾，先入右再入左，入头部
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = leftToRight ? deque.pollFirst() : deque.pollLast();
                level.add(cur.val);
                if (leftToRight) {
                    if (cur.left != null) {
                        deque.addLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.addLast(cur.right);
                    }
                } else {
                    if (cur.right != null) {
                        deque.addFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.addFirst(cur.left);
                    }
                }
            }
            leftToRight = !leftToRight;
            res.add(level);
        }
        return res;
    }
}
