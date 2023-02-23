// https://leetcode.com/problems/trim-a-binary-search-tree/
package BST;

import Structures.TreeNode;

public class TrimBST {


    public TreeNode trimBST(TreeNode root, int low, int high) {
        return trim(root, low, high);
    }

    public TreeNode trim(TreeNode node, int low, int high) {
        if (node == null) {
            return null;
        }
        if (node.val >= low && node.val <= high) {
            // 如果在区间内，左节点一定是左子树的剪枝结果
            node.left = trim(node.left, low, high);
            // 右节点一定是右子树的剪枝结果
            node.right = trim(node.right, low, high);
            return node;
        } else if (node.val < low) {
            // 如果小于low，说明右子树可能需要剪枝，返回右子树的剪枝结果
            return trim(node.right, low, high);
        } else {
            // 如果大于high，说明左子树需要剪枝，返回左子树的剪枝结果
            return trim(node.left, low, high);
        }
    }

}
