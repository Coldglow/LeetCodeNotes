// https://leetcode.com/problems/reconstruct-itinerary/
package BackTracking;

import java.util.*;

public class ReconstructItinerary {
    private List<String> res = new LinkedList<>();
    private HashMap<Integer, Boolean> used = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // 对tickets列表排序
//        tickets.sort(Comparator.comparing(a -> a.get(1)));
        tickets.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(1).compareTo(o2.get(1));
            }
        });
        this.res.add("JFK");
        construct(tickets, "JFK");
        return this.res;
    }

    // 可能存在 有机票没有用到的情况
    // 确保所有机票都用到
    // 所以返回类型不能是void
    public boolean construct(List<List<String>> tickets, String next) {
        // 如果已经用过的票等于所有的票  返回结果
        if (this.used.size() == tickets.size()) {
            return true;
        }
        for (int i = 0; i < tickets.size(); ++i) {
            if (this.used.getOrDefault(i, false) || !tickets.get(i).get(0).equals(next)) {
                continue;
            }
            this.res.add(tickets.get(i).get(1));
            this.used.put(i, true);  // 标记第i张票用过了
            // 如果返回true 说明全都用过了
            if (construct(tickets, tickets.get(i).get(1))) {
                return true;
            }
            // 如果返回false，说说明现在的路径不对，删除当前票
            this.res.remove(this.res.size() - 1);
            this.used.remove(i);
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * ["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]
         */
        List<String> t1 = new ArrayList<>();
        t1.add("JFK");
        t1.add("SFO");
        List<String> t2 = new ArrayList<>();
        t2.add("JFK");
        t2.add("ATL");
        List<String> t3 = new ArrayList<>();
        t3.add("SFO");
        t3.add("ATL");
        List<String> t4 = new ArrayList<>();
        t4.add("ATL");
        t4.add("JFK");
        List<String> t5 = new ArrayList<>();
        t5.add("ATL");
        t5.add("SFO");
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(t1);
        tickets.add(t2);
        tickets.add(t3);
        tickets.add(t4);
        tickets.add(t5);
        ReconstructItinerary o = new ReconstructItinerary();
        List<String> res = o.findItinerary(tickets);
        for (String str : res) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
