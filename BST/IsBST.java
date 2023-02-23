// https://leetcode.com/problems/validate-binary-search-tree/
package BST;

import Structures.TreeNode;

import java.util.List;

public class IsBST {

    // 递归实现
    public boolean isValidBSTRec(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        // 力扣上需要改成long类型才能通过
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 规定节点的节点的上下边界  (lower, right)  注意是开区间
    // 因为一般情况下二叉搜索树上不能有重复值
    public boolean check(TreeNode node, int lower, int upper) {
        if (node == null) {
            return true;
        }
        // 如果节点值不位于区间内 返回false
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 节点位于区间内，查询左右子树
        // 查询左子树的时候，因为左子树的值都比该节点小，所以upper变成node.val
        // 右子树相反
        return check(node.left, lower, node.val) && check(node.right, node.val, upper);
    }

    // 中序遍历  必须是一直升序
    private long preVal = Long.MIN_VALUE;
    public boolean isValidBSTInorder(TreeNode root) {
        if (root.left == null && root.right == null) {
            return true;
        }
        return check2(root);
    }

    public boolean check2(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!check2(node.left)) {
            return false;
        }
        // 二叉搜索树的中序遍历是升序
        if (this.preVal < node.val) {
            this.preVal = node.val;
        } else {
            return false;
        }
        return check2(node.right);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        IsBST o = new IsBST();
        System.out.println(o.isValidBSTRec(n1));
    }
}
