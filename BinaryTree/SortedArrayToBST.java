// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
package BinaryTree;

import Structures.TreeNode;

public class SortedArrayToBST {
    // 因为是严格递增的，所以可以得出每个节点的左节点是左侧区域的中间值，
    // 右节点是右侧区域的中间值
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }
        return buildBST(nums, 0, nums.length - 1);
    }

    public TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = ((left + right) >> 1);
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, left, mid - 1);
        node.right = buildBST(nums, mid + 1, right);
        return node;
    }
}
