
import Structures.*;

import java.util.*;


public class Main {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        TreeNode cur;
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                cur = deque.pollFirst();
                if (cur.right != null) {
                    deque.addLast(cur.right);
                }
                if (cur.left != null){
                    deque.addLast(cur.left);
                }
                if (i == 0) {
                    res.add(cur.val);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "";
//        ArrayList<Integer> a = new ArrayList<>(6);
//        a.add(0, 5);
//        a.add(0,4);
//        a.add(0, 3);
//        a.add(0,2);
//        a.add(0, 1);
//        a.add(0,0);
//        a.add(0,-1);
//        for (int i : a) {
//            System.out.println(i);
//        }
//        int[] arr = new int[]{3,2,1,5,6,4};
        Main o = new Main();
//        o.quickSort(arr, 0, arr.length - 1);
        int[] arr = new int[] {1, 2, 3};
//        Permutations o = new Permutations();

    }
}
