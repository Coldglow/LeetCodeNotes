package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author David Wong
 * @date 27/02/2023 13:08
 * Scanner in = new Scanner(System.in);
 */
public class LargestNumOfSameTree {
    private Deque<TreeNode> deque = new ArrayDeque<>();
    public int maxSubTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode cur = deque.pollFirst();
            res = Math.max(check(cur.left, cur.right, 0), res);
        }
        return res;
    }

    public int check(TreeNode left, TreeNode right, int n) {
        if (!(left != null && right != null)) {
            if (left != null) {
                deque.addLast(left);
            }
            if (right != null) {
                deque.addLast(right);
            }
            return n;
        }
        int leftNum = check(left.left, right.left, n + 1);
        int rightNum = check(left.right, right.right, n + 1);
        return Math.max(leftNum, rightNum);
    }
}
