// https://leetcode.com/problems/same-tree/
package BinaryTree;

import Structures.TreeNode;

public class SameTree {
    // 这道题和判断对称思路一样  也可以用递归和非递归，非递归用队列
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return check(p, q);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null) {
            if (p.val == q.val) {
                return check(p.left, q.left) && check(p.right, q.right);
            }
        }
        return false;
    }
}
