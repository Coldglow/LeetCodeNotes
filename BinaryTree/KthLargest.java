// https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
package BinaryTree;

import Structures.TreeNode;

import java.util.Stack;

public class KthLargest {
    /**
     * 二叉搜索树的中序是升序，那么中序的逆序就是降序，只需要把left的位置换成right
     * right的位置换成left就成了中序的逆序
     * @param root t
     * @param k t
     * @return t
     */
    public int kthLargest1(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int n = 0;
        int res = 0;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.right;
            } else {
                root = stack.pop();
                if (++n == k) {
                    res = root.val;
                    break;
                }
                root = root.left;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(2);
//        TreeNode node5 = new TreeNode(8);
        // 2 4 1 5 3
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
//        node3.left = node5;
        KthLargest obj = new KthLargest();
        System.out.println(obj.kthLargest1(node1, 1));

    }
}
