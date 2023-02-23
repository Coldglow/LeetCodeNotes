// https://leetcode.com/problems/invert-binary-tree/
package BST;

import Structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertTree {
    // 递归
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    public void invert(TreeNode node) {
        if (node == null) {
            return;
        }
        invertTree(node.left);
        invertTree(node.right);

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }

    // 迭代
    // 用队列，如果左右子树都为空，跳过，否则交换左右子树，然后子树进入队列
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null && cur.right == null) {
                continue;
            }
            // 交换左右子树
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            // 左右子树进队列
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return root;
    }
}
