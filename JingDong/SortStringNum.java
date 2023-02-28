package JingDong;

import java.util.*;

/**
 * @author David Wong
 * @date 25/02/2023 11:38
 */
public class SortStringNum {
    private int res = 0;
    private static int[] used;
    private List<String> oneAns = new LinkedList<>();
    public void process(String[] str) {
        used = new int[str.length];
        sortStrings(str, 0);
    }
    public void sortStrings(String[] str, int index) {
        if (index == str.length) {
            res++;
//            for (String s : str) {
//                System.out.print(s + " ");
//            }
//            System.out.println();
            return;
        }
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < str.length; i++) {
            if (used[i] == 1 || set.contains(str[i])) {
                continue;
            }
            used[i] = 1;
            oneAns.add(str[i]);
            set.add(str[i]);
            sortStrings(str, i + 1);
            used[i] = 0;
            oneAns.remove(oneAns.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] str = new String[n];
        HashMap<Integer,HashSet<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            str[i] = in.next();
            if (map.containsKey(str[i].length())) {
                map.get(str[i].length()).add(str[i]);
            } else {
                map.put(str[i].length(), new HashSet<>());
                map.get(str[i].length()).add(str[i]);
            }
        }
        double sum = 1;
        for (Map.Entry<Integer, HashSet<String>> entry : map.entrySet()) {
            int size = entry.getValue().size();
            if (size > 1) {
                int t = size * (size - 1) / 2;
                sum += (sum * t) % Math.pow(10, 9) + 7;
            }
        }
        System.out.print((int)(sum));
    }
}
