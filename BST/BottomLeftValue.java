// https://leetcode.com/problems/find-bottom-left-tree-value/
package BST;

import Structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BottomLeftValue {
    // BFS
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur, res = root;
        while (!queue.isEmpty()) {
            res = queue.peek();
            int size = queue.size();
            while (size-- > 0) {
                cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return res.val;
    }

    // DFS
    private int curHeight = 0;
    private int curVal = 0;
    public int findBottomLeftValue2(TreeNode root) {
        process(root, 0);
        return curVal;
    }

    public void process(TreeNode node, int height) {
        if (node == null) {
            return;
        }
        height++;
        process(node.left, height);
        process(node.right, height);
        if (height > curHeight) {
            curHeight = height;
            curVal = node.val;
        }
    }
}
