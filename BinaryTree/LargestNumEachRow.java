//https://leetcode.com/problems/find-largest-value-in-each-tree-row/
package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LargestNumEachRow {
    // 深度优先
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        dfs(res, 0, root);
        return res;
    }

    public void dfs(List<Integer> res, int curHeight, TreeNode node) {
        if (curHeight == res.size()) {
            // 因为高度从0开始，所以当高度等于链表长度的时候
            // 说明到达了新的一层，直接将该层第一个值加入到链表尾部
            res.add(node.val);
        } else {
            // 如果当前高度小于链表长度，说明当前高度之前存在了一个值
            // 比较取较大值
            res.set(curHeight, Math.max(res.get(curHeight), node.val));
        }
        // 然后向下一层遍历
        if (node.left != null) {
            dfs(res, curHeight + 1, node.left);
        }
        if (node.right != null) {
            dfs(res, curHeight + 1, node.right);
        }
    }

    // 广度优先
    public List<Integer> largestValuesLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        deque.addLast(root);
        TreeNode cur;
        while (!deque.isEmpty()) {
            int size = deque.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                max = Math.max(max, cur.val);
            }
            res.add(max);
        }
        return res;
    }
}
