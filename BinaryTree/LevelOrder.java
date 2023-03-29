// https://leetcode.cn/problems/binary-tree-level-order-traversal/
package BinaryTree;

import Structures.TreeNode;

import java.util.*;

public class LevelOrder {
    /**
     * 二叉树层次遍历  用队列
     * 用一个变量n来记录当前层有多少个节点，遍历n次
     * 然后进入下一层
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int n = deque.size();
            List<Integer> oneLevel = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                oneLevel.add(cur.val);
            }
            res.add(oneLevel);
        }
        return res;
    }

    // 第二次写  没忘
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        TreeNode node = root;
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(node);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> oneLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.pollFirst();
                oneLevel.add(cur.val);
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
            }
            res.add(oneLevel);
        }
        return res;
    }
}
