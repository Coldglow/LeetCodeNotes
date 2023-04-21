package BinaryTree;

import Structures.TreeNode;

/**
 * @author David Wong
 * @date 21/04/2023 10:38
 * Scanner in = new Scanner(System.in);
 */
public class SumNumbers {
    /*
    因为val都是个位数并且节点数量不超过1000, 所以不会溢出
    如果没有这些限制, 需要考虑溢出的情况, 就先需要使用List记录根节点到叶子节点的数, 字符串的形式
    然后再遍历list字符串相加
     */
    private int res = 0;
    private StringBuilder sb;
    public int sumNumbers(TreeNode root) {
        sb = new StringBuilder();
        DFS(root);
        return res;
    }

    public void DFS(TreeNode node) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            res += Integer.parseInt(new String(sb));
            return;
        }
        if (node.left != null) {
            DFS(node.left);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (node.right != null) {
            DFS(node.right);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}
