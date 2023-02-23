// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
package BST;

import Structures.TreeNode;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q);
    }

    // 想清楚base case：空，p或者q，三者满足一个都返回node自身
    // 如果左不为空，那么左一定是p或者q中的一个
    // 右同理
    // 如果左右都不为空，那么一定是最lca，返回node
    // 对于p是q的孩子这种情况，一定会返回而这种的父节点，所以无需特别考虑这种情况
    public TreeNode lca(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q) {
            return node;
        }
        TreeNode left = lca(node.left, p, q);
        TreeNode right = lca(node.right, p, q);
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }


    // 第二次写  有些印象 但是不确定 还是看了下第一次写的
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
