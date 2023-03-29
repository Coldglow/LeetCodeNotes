// https://leetcode.com/problems/minimum-depth-of-binary-tree/
package BinaryTree;

import Structures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 注意这里不是在循环执行完成后size才--  而是在判断完成后就--
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth + 1;
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                if (size == 0) {
                    depth++;
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        MinimumDepth o = new MinimumDepth();
        System.out.println(o.minDepth(n1));
    }
}
