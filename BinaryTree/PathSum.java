// https://leetcode.cn/problems/find-bottom-left-tree-value/
package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSum {
//    private int sum = 0;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum, 0);
    }

    public boolean process(TreeNode node, int target, int sum) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return sum + node.val == target;
        }
        return process(node.left, target, sum + node.val) ||
                process(node.right, target, sum + node.val);
    }


    // https://leetcode.com/problems/path-sum-ii/description/
    private List<List<Integer>> paths;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        getPath(root, targetSum, 0, path);
        return paths;
    }

    public void getPath(TreeNode node, int target, int sum, List<Integer> path) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (sum + node.val == target) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(node.val);
                this.paths.add(newPath);
            }
            return;
        }
        path.add(node.val);
        getPath(node.left, target, sum + node.val, path);
        getPath(node.right, target, sum + node.val, path);
        path.remove(path.size() - 1);
    }
}


/*
2023.05.02
 */
class PathSum02 {
    private List<List<Integer>> res = new LinkedList<>();
    private List<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        getPath(root, 0, targetSum);
        return res;
    }

    public void getPath(TreeNode node, int sum, int target) {
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (sum + node.val == target) {
                res.add(new LinkedList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }

        if (node.left != null) {
            getPath(node.left, sum + node.val, target);
        }

        if (node.right != null) {
            getPath(node.right, sum + node.val, target);
        }

        path.remove(path.size() - 1);
    }
}
