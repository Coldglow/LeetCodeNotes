// https://leetcode.com/problems/find-mode-in-binary-search-tree/
package BinaryTree;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindMode {
    // 利用BST中序遍历是升序的性质
    // 维护上一个值出现的次数
    // 如果当前值不等于当前值，判断上一个值出现次数是否大于链表中最后一个元素出现的次数
    // 如果大于，则清空链表添加，如果等于，则添加
    private List<Integer> list = new ArrayList<>();
    private int count = 0;  // 当前值出现的次数
    private int preCount = 0;  // 上一个出现的次数最多的值出现的次数
    private int preVal = Integer.MAX_VALUE;  // 上一个众数
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (node.val == this.preVal) {
            this.count++;
        } else {
            this.count = 1;
            this.preVal = node.val;
        }

        if (this.count == this.preCount) {
            this.list.add(node.val);
        }
        if (this.count > this.preCount) {
            this.preCount = this.count;
            this.list.clear();
            this.list.add(node.val);
        }
        dfs(node.right);
    }

    /**
     * 和上面的思路一样，优化之处在于遍历方式，使用Morris遍历，空间复杂度降到了O(1)
     * @param root root
     * @return in
     */
    public int[] findMode2(TreeNode root) {
        morris(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    public void morris(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
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
            // 在这里维护出现次数和最大值
            update(cur.val);
            cur = cur.right;
        }
    }

    public void update(int val) {
        if (val == this.preVal) {
            this.count++;
        } else {
            this.preVal = val;
            this.count = 1;
        }
        if (this.count == this.preCount) {
            this.list.add(val);
        }
        if (this.count > this.preCount) {
            this.list.clear();
            this.list.add(val);
            this.preCount = this.count;
        }
    }
}
