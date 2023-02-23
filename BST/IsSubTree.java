// https://leetcode.com/problems/subtree-of-another-tree/
package BST;

import Structures.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IsSubTree {
    // 纯暴力
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (checkSub(cur, subRoot)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkSub(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root != null && subRoot != null) {
            if (root.val == subRoot.val) {
                return checkSub(root.left, subRoot.left) && checkSub(root.right, subRoot.right);
            }
        }
        return false;
    }

///////////////////////////////////////////////////////
    // KMP 方法
    private int maxVal = Integer.MIN_VALUE;
    List<Integer> rootList, subList;

    // 将空位置加上null，然后先序唯一确定树和子树的字符串，然后kmp判断是否是子串即可
    // 因为节点可能有负数，所以不能用字符串类型数组，只能用int
    // 但是需要将null替换成一个数中没有出现过的值
    // 否则在得到next数组的时候，如果next[i]是null，会报空指针错
    public boolean isSubTree2(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        rootList = new ArrayList<>();
        subList = new ArrayList<>();
        // 经过两次遍历，maxVal就等于两棵树中的最大值
        // 那么在生成序列的时候将空指针赋值为maxVal + 1即可分辨
        getMaxVal(root);
        getMaxVal(subRoot);
        // 生成序列
        getSequence(root, this.rootList);
        getSequence(subRoot, this.subList);
        for (Integer num : rootList) {
            System.out.print(num + " ");
        }
        System.out.println();
        for (Integer num : subList) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] next = getNext(subList);
        for (int num : next) {
            System.out.print(num + " ");
        }
        System.out.println();
        return kmp(rootList, subList, next);
    }

    public void getMaxVal(TreeNode node) {
        if (node == null) {
            return;
        }
        this.maxVal = Math.max(node.val, this.maxVal);
        getMaxVal(node.left);
        getMaxVal(node.right);
    }

    public void getSequence(TreeNode node, List<Integer> res) {
        if (node == null) {
            res.add(this.maxVal + 1);
            return;
        }
        res.add(node.val);
        getSequence(node.left, res);
        getSequence(node.right, res);
    }

    public int[] getNext(List<Integer> subList) {
        int[] next = new int[subList.size() + 1];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            // 这里是 i - 1
            if (subList.get(i - 1).equals(subList.get(cn))) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public boolean kmp(List<Integer> rootList,  List<Integer> subList, int[] next) {
        int i2 = 0;
        int i1 = 0;
        while (i1 < rootList.size() && i2 < subList.size()) {
            if (rootList.get(i1).equals(subList.get(i2))) {
                i2++;
                i1++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        // 注意这里不需要减一
        return i2 == subList.size();
    }

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n3.left = n4;
        n3.right = n5;
        n4.left = n1;
        n4.right = n2;

        TreeNode p4 = new TreeNode(4);
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        p4.left = p1;
        p4.right = p2;

        IsSubTree o = new IsSubTree();
        System.out.println(o.isSubTree2(n3, p4));
    }
}
