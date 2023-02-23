// https://leetcode.com/problems/symmetric-tree/
package BST;

import Structures.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class SymmetricTree {
    // 递归
    public boolean isSymmetricRec(TreeNode root) {
        if (root == null) {
            return false;
        }
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {  // 两个都是 null  直接返回true
            return true;
        }
        if (left != null && right != null) { // 如果两个都不是null 比较值
            if (left.val == right.val) {
                return check(left.left, right.right) && check(left.right, right.left);
            }
        }
        return false;
    }

    // 非递归用双端队列，先将根节点的左右孩子加入队列中，然后比较，再加入
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addFirst(root.left);
        deque.addFirst(root.right);
        while (!deque.isEmpty()) {
            TreeNode left = deque.pollFirst();
            TreeNode right = deque.pollLast();
            if (left == null && right == null) {
                continue;
            }
            if ((left == null || right == null) || left.val != right.val) {
                return false;
            }
            deque.addLast(right.left);
            deque.addFirst(left.right);
            deque.addFirst(left.left);
            deque.addLast(right.right);
        }
        return true;
    }
}
