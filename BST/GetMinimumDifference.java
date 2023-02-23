// https://leetcode.com/problems/minimum-absolute-difference-in-bst/
package BST;

import Structures.TreeNode;

public class GetMinimumDifference {
    // 二叉搜索树一定要想到中序遍历
    // 因为中序遍历是递增的，那么最小差值一定出现在相邻的两个数中
    private int minDiff = Integer.MAX_VALUE;
    // 不能设置为0  因为如果第一个数是0 则最小值就一直是0
    private int preVal = -1;
    public int getMinimumDifference(TreeNode root) {
        getMinimum(root);
        return this.minDiff;
    }

    public void getMinimum(TreeNode node) {
        if (node == null) {
            return;
        }
        getMinimum(node.left);
        if (this.preVal != -1) {
            this.minDiff = Math.min(this.minDiff, Math.abs(node.val - this.preVal));
        }
        this.preVal = node.val;
        getMinimum(node.right);
    }
}
