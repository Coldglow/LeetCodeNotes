// https://leetcode.cn/problems/delete-node-in-a-bst/
package BinaryTree;

import Structures.TreeNode;

public class DeleteInBST {
    /**
     * 如果key左子树为空： 将key右子树挂在父结点上
     * 如果key右子树为空： 将key左子树挂在父结点上
     * 如果key左右子树都存在： 找到key右子树的最左节点，将key的左子树挂在最左节点的左侧，然后将key的右子树
     *                      挂在父节点上
     * 无论哪种情况，都要判断key是父节点的左子树还是右子树
     * @param root  root
     * @param key  key
     * @return  123
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode cur = root;
        TreeNode par = new TreeNode();  // 创建两个节点 par 和 res 方便解决问题
        par.left = root;
        TreeNode res = par;   // 固定父节点，作为返回值
        while (true) {
            if (cur.val < key) {  // key 比节点大  在右子树上
                if (cur.right == null) {  // key不存在
                    break;
                }
                par = cur;
                cur = cur.right;
            } else if (cur.val > key) {  // key 在左子树上
                if (cur.left == null) {
                    break;
                }
                par = cur;
                cur = cur.left;
            } else {   // 找到key  cur.val == key
                delete(par, cur);
                break;
            }
        }
        return res.left;
    }

    public TreeNode getMostLeft(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void delete(TreeNode par, TreeNode cur) {
        if (cur.left == null) {
            if (cur == par.left) {
                par.left = cur.right;  // 如果cur是par的左孩子同时cur没有左子树
            } else {
                par.right = cur.right;
            }
        } else if (cur.right == null) {
            if (cur == par.left) {
                par.left = cur.left;  // 如果cur是par的左孩子同时cur没有左子树
            } else {
                par.right = cur.left;
            }
        } else {   // cur左右子树都存在 将左子树挂在右子树的最左节点上，右子树挂在父节点上
            TreeNode mostLeft = getMostLeft(cur.right);  // 找到右子树的最左节点
            mostLeft.left = cur.left;  // 将cur的左子树挂在右子树的最左节点的左节点
            if (cur == par.left) {
                par.left = cur.right;
            } else {
                par.right = cur.right;
            }
        }
    }
}
