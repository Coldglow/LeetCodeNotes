// https://leetcode.com/problems/average-of-levels-in-binary-tree/
package BST;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AverageOfLevels {
    // 求每层的平均值
    // 层次遍历累计和然后除以每层大小
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Deque<TreeNode> deque = new LinkedList<>();
        List<Double> res = new ArrayList<>();
        deque.addLast(root);
        TreeNode cur;
        while (!deque.isEmpty()) {
            int size = deque.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                sum += cur.val;
            }
            res.add(sum / size);
        }
        return res;
    }
}
