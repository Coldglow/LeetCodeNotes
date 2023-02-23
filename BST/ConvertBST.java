// https://leetcode.com/problems/convert-bst-to-greater-tree/
package BST;

import Structures.TreeNode;

public class ConvertBST {
    // 反序中序遍历  递归
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convert(root, 0);
        return root;
    }

    public int convert(TreeNode node, int preSum) {
        if (node == null) {
            return preSum;
        }
        node.val += convert(node.right, preSum);;
        preSum = node.val;
        return convert(node.left, preSum);
    }

    // 逆Morris 中序遍历  只需要在第二次访问的地方修改node值即可
    // 需要一个变量记录累加和  累加和在第一次访问节点的时候累加
    // 但是在第二次访问节点的时候修改节点的值
    public TreeNode convertBST2(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode cur = root;
        TreeNode mostLeft;
        int sum = 0;
        while (cur != null) {
            mostLeft = cur.right;
            if (mostLeft != null) {
                while (mostLeft.left != null && mostLeft.left != cur) {
                    mostLeft = mostLeft.left;
                }
                if (mostLeft.left == null) {
                    mostLeft.left = cur;
                    cur = cur.right;
                    continue;
                } else {
                    mostLeft.left = null;
                }
            }
            cur.val += sum;
            sum = cur.val;
            cur = cur.left;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(3);
        TreeNode n9 = new TreeNode(8);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.right = n8;
        n7.right = n9;
        ConvertBST o = new ConvertBST();
        System.out.println(o.convertBST2(n1).val);
    }
}
