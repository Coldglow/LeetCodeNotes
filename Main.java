
import Structures.*;

import java.util.*;


public class Main {

    private List<List<Integer>> res;
    private List<Integer> subSet;

    public List<List<Integer>> subsets(int[] nums) {
        res = new LinkedList<>();
        res.add(new LinkedList<>());
        subSet = new LinkedList<>();
        backTracking(nums, 0);
        return res;
    }

    public void backTracking(int[] arr, int index) {
        for (int i = index; i < arr.length; i++) {
            subSet.add(arr[i]);
            res.add(new LinkedList<>(subSet));
            backTracking(arr, i + 1);
            subSet.remove(subSet.size() - 1);
        }
    }


    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            int a = in.nextInt();
//            int b = in.nextInt();
//            System.out.println(a + b);
//        }

        String s = "   123   ";

        System.out.println(s.trim());
//        String s = "aab";
//        Main o = new Main();
//        int[] res = o.printNum();
//        for (int n : res) {
//            System.out.print(n + " ");
//        }
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

    }
}
