package BinaryTree;

import Structures.TreeNode;

/**
 * @author David Wong
 * @date 27/03/2023 10:31
 * Scanner in = new Scanner(System.in);
 */
public class MaxPathSum {

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        DFS(root);
        return maxSum;
    }

    public int DFS(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 如果这个节点做的贡献为负，直接让其等于0
        int left = Math.max(DFS(node.left), 0);
        int right = Math.max(DFS(node.right), 0);
        maxSum = Math.max(node.val + left + right, maxSum);

        return node.val + Math.max(left, right);
    }

}
