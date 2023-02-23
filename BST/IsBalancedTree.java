// https://leetcode.com/problems/balanced-binary-tree/
package BST;

import Structures.TreeNode;

public class IsBalancedTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkBalance(root) != -1;
    }

    // 另一种写法就是把高度和子树是否平衡这两个信息放在一个结构里面
    // 每次返回都new出来一个新的结构，里面包含子树的高度和是否平衡
    public int checkBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = checkBalance(node.left);
        if (left == -1) {
            return -1;
        }

        int right = checkBalance(node.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        } else {
            return -1;
        }
    }
}
