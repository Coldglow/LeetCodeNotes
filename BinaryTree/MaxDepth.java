// https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
package BinaryTree;

import Structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepth {
    // BFS
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> deque = new LinkedList<>();
        int depth = 0;
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode cur = deque.poll();
                if (cur.left != null) {
                    deque.add(cur.left);
                }
                if (cur.right != null) {
                    deque.add(cur.right);
                }
            }
            depth++;
        }
        return depth;
    }

    // DFS
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, 0, 0);
    }

    public int dfs(TreeNode node, int depth, int curHeight) {
        if (node == null) {
            depth = Math.max(depth, curHeight);
            return depth;
        }
        int leftDepth = dfs(node.left, depth, curHeight+ 1);
        int rightDepth = dfs(node.right, depth, curHeight + 1);
        depth = Math.max(Math.max(leftDepth, rightDepth), depth);
        return depth;
    }
}
