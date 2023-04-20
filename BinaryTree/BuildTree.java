package BinaryTree;

import Structures.TreeNode;

import java.util.HashMap;

/**
 * @author David Wong
 * @date 20/04/2023 08:20
 * Scanner in = new Scanner(System.in);
 */
public class BuildTree {
    private HashMap<Integer, Integer> inMap;
    private int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        build(root, preorder, 0, inorder.length - 1);
        return root;
    }

    public void build(TreeNode node, int[] preorder, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = inMap.get(node.val);
        // 构建左子树
        if (i > left) {
            preIndex += 1;
            node.left = new TreeNode(preorder[preIndex]);
            // left不变, right变成 inMap.get(node.val) - 1
            build(node.left, preorder, left, i - 1);
        }
        if (i < right) {
            // 构建右子树
            preIndex += 1;
            node.right = new TreeNode(preorder[preIndex]);
            build(node.right, preorder, i + 1, right);
        }
    }
}
