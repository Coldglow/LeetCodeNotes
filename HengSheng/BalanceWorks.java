package HengSheng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author David Wong
 * @date 23/03/2023 11:25
 * Scanner in = new Scanner(System.in);
 */
public class BalanceWorks {

    class WorkQueue {
        public int workCount;
        public ArrayList<Integer> worksList;

        public WorkQueue() {
            this.workCount = 0;
            this.worksList = new ArrayList();
        }
    }



    public void balanceWork(int[] works, int workerCount) {
        // 排序
        Arrays.sort(works);
        ArrayList<WorkQueue> res = new ArrayList<>();
        // 创建worker列表
        for (int i = 0; i < workerCount; i++) {
            res.add(new WorkQueue());
        }

        // 逆序遍历works列表，循环遍历res列表
        int flag = 0;
        for (int i = works.length - 1; i >= 0; i--) {
            // 循环遍历worker列表添加
            if (flag == 0) {
                for (int j = 0; j < workerCount; j++) {
                    res.get(j).worksList.add(works[i]);
                    res.get(j).workCount += works[i];
                }
                flag = 1;
            } else {
                for (int j = workerCount - 1; j >= 0; j--) {
                    res.get(j).worksList.add(works[i]);
                    res.get(j).workCount += works[i];
                }
                flag = 0;
            }
        }

    }

    public static void main(String[] args) {

    }
}
