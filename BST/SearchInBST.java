// https://leetcode.com/problems/search-in-a-binary-search-tree/
package BST;

import Structures.TreeNode;

public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode res = root;
        while (res != null && res.val != val) {
            if (res.val < val) {
                res = res.right;
            } else {
                res = res.left;
            }
        }
        return res;
    }
}
