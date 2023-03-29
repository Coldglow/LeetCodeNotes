// https://leetcode.com/problems/insert-into-a-binary-search-tree/
package BinaryTree;

import Structures.TreeNode;

public class InsertIntoBST {
    /**
     * 别想复杂  BST树的插入可以只插入到叶子节点
     * 只有AVL树需要左旋右旋操作
     * @param root  根节点
     * @param val  值
     * @return  根节点
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;
        while (true) {
            if (cur.val > val) {
                if (cur.left == null) {
                    cur.left = new TreeNode(val);
                    break;
                }  else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(val);
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;
    }
}
