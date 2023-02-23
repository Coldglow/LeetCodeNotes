// https://leetcode.com/problems/binary-tree-right-side-view/
package BST;

import Structures.TreeNode;

import java.util.*;

public class RightSideView {
    // 其实就是层次遍历的最后一个节点
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        deque.add(root);
        TreeNode cur;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                if (size == 0) {
                    res.add(cur.val);
                }
            }
        }
        return res;
    }
}
