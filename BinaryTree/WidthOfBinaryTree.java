package BinaryTree;

import Structures.TreeNode;

import java.util.LinkedList;

/**
 * @author David Wong
 * @date 23/06/2023 15:37
 * Scanner in = new Scanner(System.in);
 */
public class WidthOfBinaryTree {

    class Pair<K, V> {
        public K node;
        public V val;

        public Pair(K node, V val) {
            this.node = node;
            this.val = val;
        }
    }

    /**
     * 二叉树层次遍历, 记录每一层的开始节点和中间节点, 记录每个节点的编号, 然后最大的编号 - 最小的编号 + 1 就是之间的长度
     * 同时编号要用Long, int会溢出
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<Pair<TreeNode, Long>> deque = new LinkedList<>();
        deque.add(new Pair<>(root, 1L));
        int res = 0;
        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            long maxNum = Long.MIN_VALUE, minNum = Long.MAX_VALUE;
            while (levelSize != 0) {
                Pair<TreeNode, Long> pair = deque.pollFirst();
                TreeNode cur = pair.node;
                Long num = pair.val;
                if (cur.left != null) {
                    deque.addLast(new Pair<>(cur.left, num * 2));
                }
                if (cur.right != null) {
                    deque.addLast(new Pair<>(cur.right, num * 2 + 1));
                }
                minNum = Math.min(minNum, num);
                maxNum = Math.max(maxNum, num);
                levelSize--;
            }
            res = (int) Math.max(res, maxNum - minNum + 1);
        }
        return res;
    }


    public int widthOfBinaryTree2(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int res = 0;
        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            while (levelSize != 0) {
                TreeNode cur = deque.pollFirst();
                if (cur.left != null) {
                    deque.addLast(cur.left);
                }
                if (cur.right != null ){
                    deque.addLast(cur.right);
                }
                levelSize--;
            }
        }
        return res;
    }
}
