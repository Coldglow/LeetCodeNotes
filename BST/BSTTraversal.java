// 二叉树的前序中序后序，递归和非递归
// 层次遍历
// Morrison遍历
package BST;

import Structures.TreeNode;

import java.util.*;

public class BSTTraversal {
    // 大框架
    public void TraversalRec(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序
        TraversalRec(root.left);
        // 中序
        TraversalRec(root.right);
        // 后序
    }

    // 前序递归
    public List<Integer> preTraversalRec(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preTraversal(root, res);
        return res;
    }

    public void preTraversal(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        preTraversal(node.left, res);
        preTraversal(node.right, res);
    }

    // 前序非递归
    // 先压右节点，再压左节点，一直到栈不为空
    public List<Integer> preUnRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    // 中序非递归
    // 一直向左走，走到空，然后弹出，cur指向栈顶结点的右节点，继续循环
    // 注意while的条件
    public List<Integer> inUnRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        // 记住这个条件
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    // 后序非递归
    // 先序是先左再右  后序是 先右再左，另外后序是两个栈，一个输出栈一个输入栈
    public List<Integer> posUnRec(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<TreeNode> inStack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur;
        inStack.push(root);
        while (!inStack.isEmpty()) {
            cur = inStack.pop();
            if (cur.left != null) {
                inStack.push(cur.left);
            }
            if (cur.right != null) {
                inStack.push(cur.right);
            }
            outStack.push(cur);
        }
        while (!outStack.isEmpty()) {
            res.add(outStack.pop().val);
        }
        return res;
    }

    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    // 层次遍历 用队列
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        TreeNode cur;
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> oneLevel = new ArrayList<>();
            while (size-- > 0) {
                cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                oneLevel.add(cur.val);
            }
            res.add(oneLevel);
        }
        return res;
    }

    // 层次遍历
    // 从叶节点层到根节点
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        TreeNode cur;
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> oneLevel = new ArrayList<>();
            while (size-- > 0) {
                cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                oneLevel.add(cur.val);
            }
            res.add(0, oneLevel);
        }
        return res;
    }
}
