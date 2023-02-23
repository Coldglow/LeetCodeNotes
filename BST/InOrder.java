package BST;

import Structures.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InOrder {
    /**
     * 2023.2.21  没写出来，while的条件没写出来
     * @param root  root
     * @return 123
     */
    // 栈
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        // 这里的条件，或关系
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }

    // morris   2023.2.21 没写出来
    public List<Integer> inOrderM(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        TreeNode cur = root;
        List<Integer> res = new LinkedList<>();
        while (cur != null) {
            TreeNode mostRight = cur.left;
            if (cur.left != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

}
