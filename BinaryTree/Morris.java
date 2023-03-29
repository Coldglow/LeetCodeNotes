/**
 * 二叉树Morris遍历  Morris遍历会访问两次非叶子节点  一次叶子节点
 */
package BinaryTree;

import Structures.TreeNode;

public class Morris {
    /**
     * 按照大框架来写
     * 1. 初始化cur = root mostRight = null
     * 2. while大循环，cur ！= null
     * 3. mostRight = cur.left
     * 4. 如果mostRight ！= null {...}   如果cur没有左孩子或者有左孩子但是访问完成， cur右移  cur = cur.right
     * 5. 写有左孩子的操作
     *      1) 先找到左子树的最右节点  注意 while的条件，mostRight.right如果为空或者为cur就停止
     *      2)
     * @param root   root
     */
    public void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 如果mostRight指向cur说明是第二次来到cur已经访问过了
                    // mostRight.right置空  cur右移
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    // Morris改中序：对于只访问一次的节点，在访问的时候就打印
    // 对于访问两次的节点，在第二次访问的时候打印
    public void inOrderMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次访问cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {  // 第二次访问cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
}
