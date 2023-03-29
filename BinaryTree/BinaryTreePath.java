// https://leetcode.com/problems/binary-tree-paths/
package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> res = new LinkedList<>();
        getPaths(root, res, "");
        return res;
    }

    public void getPaths(TreeNode node, List<String> paths, String s) {
        // 只能每次遍历到一个新节点，把之前的路径都变成StringBuilder
        if (node != null) {
            StringBuilder sb = new StringBuilder(s);
            sb.append(node.val);
            if (node.left == null && node.right == null) {
                paths.add(sb.toString());
            } else {
                sb.append("->");
                getPaths(node.left, paths, sb.toString());
                getPaths(node.right, paths, sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        BinaryTreePath o = new BinaryTreePath();
        List<String> res = o.binaryTreePaths(n1);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
