// https://leetcode.com/problems/sum-of-left-leaves/
package BinaryTree;

import Structures.TreeNode;

public class SumOfLeftLeaves {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 虚拟节点
        TreeNode dummy = new TreeNode();
        dummy.right = root;
        getSum(root, dummy);
        return sum;
    }

    public void getSum(TreeNode cur, TreeNode par) {
        if (cur == null) {
            return;
        }
        if (par.left == cur && cur.left == null && cur.right == null) {
            sum += cur.val;
        }
        getSum(cur.left, cur);
        getSum(cur.right, cur);
    }

    // 另一种递归写法
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 虚拟节点
        TreeNode dummy = new TreeNode();
        dummy.right = root;
        return getSum2(root, dummy, 0);
    }
    public int getSum2(TreeNode cur, TreeNode par, int sum) {
        if (cur == null) {
            return 0;
        }
        if (par.left == cur && cur.left == null && cur.right == null) {
            return sum + cur.val;
        }
        return getSum2(cur.left, cur, sum) + getSum2(cur.right, cur, sum);
    }
}
