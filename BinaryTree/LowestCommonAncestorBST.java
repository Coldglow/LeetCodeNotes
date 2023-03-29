// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
package BinaryTree;

import Structures.TreeNode;

public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lower = Math.max(p.val, q.val) == p.val ? q : p;
        TreeNode upper = lower == q ? p : q;
        // 确保第二个参数指向二者中的较小值
        // 第三个参数指向二者中的较大值
        return lcaBST(root, lower, upper);
    }

    public TreeNode lcaBST(TreeNode node, TreeNode lower, TreeNode upper) {
        if (node == null || node == lower || node == upper) {
            return node;
        }

        TreeNode left = node.val < lower.val ? null : lcaBST(node.left, lower, upper);
        TreeNode right = node.val > upper.val ? null : lcaBST(node.right, lower, upper);
        if (left != null && right != null) {
            return node;
        }

        return left != null ? left : right;
    }

    // 也可以不用递归, 根据二叉搜索树的性质，第一个位于(lower, upper)之间的数就是其最小公共祖先
    // 如果 node 大于 p和q  node左移
    // 如果node 小于p和q   node右移
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = root;
        while (true) {
            if (p.val < res.val && q.val < res.val) {
                res = res.left;
            } else if (res.val < p.val && res.val < q.val) {
                res = res.right;
            } else {
                break;
            }
        }
        return res;
    }

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = root;
        while (true) {
            if (p.val > res.val && q.val > res.val) {
                res = res.left;
            } else if (res.val > p.val && res.val > q.val) {
                res = res.right;
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(9);
        TreeNode n8 = new TreeNode(3);
        TreeNode n9 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.left = n8;
        n5.right = n9;
        LowestCommonAncestorBST o = new LowestCommonAncestorBST();
        System.out.println(o.lowestCommonAncestor3(n1, n2, n3).val);
    }
}
