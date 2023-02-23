// https://leetcode.cn/problems/binary-tree-maximum-path-sum/
package BST;

import Structures.TreeNode;

/**
 * 2023.2.20  看答案的
 */
public class BinaryTreeMaximumSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 如果子节点的贡献值是负的，就把他的贡献当作0
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);
        // 计算当前节点的贡献
        int contribution = node.val + left + right;
        maxSum = Math.max(contribution, maxSum);

        return node.val + Math.max(left, right);
    }
}
