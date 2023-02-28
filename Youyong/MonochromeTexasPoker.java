package Youyong;

import java.util.*;

/**
 * @author David Wong
 * @date 25/02/2023 07:52
 */
public class MonochromeTexasPoker {
//    private HashMap<Integer, Integer> map = new HashMap<>();
    private int[] res = new int[5];
    private int[] arr = new int[14];

    public int[] monochromeTexasPoker (int[] cards) {
        int maxNum = 0;
        int maxCard = 0;
        for (int card : cards) {
            arr[card]++;
        }
        for (int i = 2; i < 14; i++) {
            if (arr[i] >= maxNum) {
                maxNum = arr[i];
                maxCard = i;
            }
        }

        if (maxNum == 4) {
            fourSame(cards, maxCard);
        } else if (maxNum == 3) {
            threeSame(maxCard);
        } else if (maxNum == 2) {
            TwoSame(maxCard);
        } else {
            noSame(maxCard);
        }
        Arrays.sort(res);
        for (int n : res) {
            System.out.print(n + " ");
        }
        return res;
    }

    public void fourSame(int[] cards, int maxCard) {
        int restMax = 0;
        int index = 13;
        while (index > 0) {
            if (arr[index] != 0 && index != maxCard) {
                restMax = index;
                break;
            }
            index--;
        }
        for (int i = 4; i > 0; i--) {
            res[i] = maxCard;
        }
        res[0] = restMax;
    }

    public void threeSame(int maxCard) {
        int second = 0, first = 0;
        int index = 13;
        while (index > 0) {
            if (arr[index] != 0 && index != maxCard) {
                if (second == 0) {
                    second = index;
                    arr[index]--;
                    index++;
                } else {
                    first = index;
                    break;
                }
            }
            index--;
        }
        for (int i = 4; i > 1; i--) {
            res[i] = maxCard;
        }
        res[1] = second;
        res[0] = first;
    }

    public void TwoSame(int maxCard) {
        int index = 13;
        res[4] = maxCard;
        res[3] = maxCard;
        while (index > 0) {
            if (arr[index] != 0 && index != maxCard) {
                if (res[2] == 0) {
                    res[2] = index;
                    arr[index]--;
                    index++;
                } else if (res[1] == 0){
                    res[1] = index;
                    arr[index]--;
                    index++;
                } else {
                    res[0] = index;
                    break;
                }
            }
            index--;
        }
    }

    public void noSame(int maxCard) {
        int index = 4;
        for (int i = 13; i > 0; i--) {
            if (arr[i] != 0) {
                res[index--] = i;
            }
            if (index == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] cards = {1,1,2,2,4,4,6};
        MonochromeTexasPoker o = new MonochromeTexasPoker();
        o.monochromeTexasPoker(cards);
    }
}
