// https://leetcode.com/problems/merge-two-binary-trees/
package BST;

import Structures.TreeNode;

public class MergeTwoBST {
    // DFS
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1, root2);
    }

    public TreeNode merge(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        if (node1 != null && node2 != null) {
            node.val = node1.val + node2.val;
            node.left = merge(node1.left, node2.left);
            node.right = merge(node1.right, node2.right);
        } else if (node1 == null) {
            // node2 不为空
            node.val = node2.val;
            node.left = merge(null, node2.left);
            node.right = merge(null, node2.right);
        } else {
            node.val = node1.val;
            node.left = merge(node1.left,  null);
            node.right = merge(node1.right,  null);
        }
        return node;
    }

    // 简化后的写法
    public TreeNode merge2(TreeNode t1, TreeNode t2) {
        // 如果两个都是空，随便返回一个就行了  不用再写一个if来判断是否都是空
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = merge(t1.left, t2.left);
        node.right = merge(t1.right, t2.right);
        return node;
    }
}
