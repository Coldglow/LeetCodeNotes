// https://leetcode.com/problems/count-complete-tree-nodes/
package BST;

import Structures.TreeNode;

import java.util.Stack;

public class CountCompleteTreeNodes {
    // DFS
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return count(root);
    }

    public int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return count(node.left) + count(node.right) + 1;
    }


    public int countNodes2(TreeNode root) {
       if (root == null) {
           return 0;
       }
       TreeNode node = root;
       int height = 0;
       while (node.left != null) {
           height++;
           node = node.left;
       }
       // 注意加减乘除的优先级比位运算优先级高
       // 注意乘2 是1左移 不是2左移
       int high = (1 << (height + 1)) - 1;
       int low = 1 << height;
       while (low < high) {
           // high - low 再加上1才是最后一层叶子节点的数量
           int mid = ((high - low + 1) >> 1) + low;
           if (check(root, height, mid)) {
               // 如果该节点存在，说明节点个数大于等于该节点编号
               // 查询右半区
               low = mid;
           } else {
               high = mid - 1;
           }
       }
       return low;
    }

    public boolean check(TreeNode node, int level, int num) {
        // 这里是level - 1  因为从根节点走到叶子节点只需要走level - 1个路径
        int bit = 1 << (level - 1);
        while (node != null && bit > 0) {
            if ((bit & num) == 0) {
                // 左走
                node = node.left;
            } else {
                // 右走
                node = node.right;
            }
            // 检查结束bit右移一位
            bit >>= 1;
        }
        return node != null;
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
        CountCompleteTreeNodes o = new CountCompleteTreeNodes();
        System.out.println(o.countNodes2(n1));
    }
}
