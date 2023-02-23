// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
package BST;

import Structures.TreeNode;

import java.util.HashMap;

public class ConstructBST {
    // 中序遍历中，根节点左边为左子树的节点，右边为右子树的节点
    // 后序遍历中，最后一个节点为根节点
    // 先通过后序遍历找到根节点，然后再找到根节点在中序遍历中的位置，将根节点分成左右两棵子树
    // 然后后序遍历的下标减1，找到根节点的右孩子
    // 循环
    // 当左子树的下标大于右子树的下标，说明该树不存在，返回null
    private HashMap<Integer, Integer> indexMap = new HashMap<>();
    private int[] postorder;
    private int[] preorder;
    private int postIndex;
    private int preIndex;



    /**
     * 根据中序遍历和后序遍历的结果还原一棵树
     * @param inorder  中序遍历的结果
     * @param postorder  后序遍历的结果
     * @return  整棵树的根节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }
        int len = postorder.length;
        this.postorder = postorder;
        this.postIndex = len - 1;
        // 构建哈希表存储值和对应的下标
        for (int i = 0; i < len; i++) {
            indexMap.put(inorder[i], i);
        }
        return construct(0, len - 1);
    }

    public TreeNode construct(int left, int right) {
        if (left > right) {
            return null;
        }
        // 选择 postIndex 位置的元素作为当前子树的根节点
        int root_val = this.postorder[postIndex];
        TreeNode root = new TreeNode(root_val);
        // 根据root的位置分成左右两棵子树
        int index = indexMap.get(root_val);
        // 下标减一  构建右子树
        this.postIndex--;
        root.right = construct(index + 1, right);
        // 构建左子树
        root.left = construct(left, index - 1);
        return root;
    }

    /**
     * 根据中序和先序的结果还原一棵树
     * @param inorder  12
     * @param preorder  321
     * @return  123
     */
    public TreeNode buildTree2(int[] inorder, int[] preorder) {
        if (inorder.length == 1) {
            return new TreeNode(inorder[0]);
        }
        int len = inorder.length;
        this.preIndex = 0;
        this.preorder = preorder;
        // 构建哈希表
        for (int i = 0; i < len; i++) {
            this.indexMap.put(inorder[i], i);
        }
        return construct2(0, len - 1);
    }

    public TreeNode construct2(int left, int right) {
        if (left > right) {
            return null;
        }
        // 找到子树根节点在中序遍历的位置
        int root_val = this.preorder[this.preIndex];
        TreeNode root = new TreeNode(root_val);
        // 根据root将中序遍历分成左右子树
        int index = this.indexMap.get(root_val);
        // 和上面不一样 因为先序遍历从左到右构建，所以先构建左子树
        this.preIndex++;
        root.left = construct2(left, index - 1);
        root.right = construct2(index + 1, right);
        return root;
    }
}
