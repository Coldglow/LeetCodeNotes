package BackTracking;

/**
 * @author David Wong
 * @date 03/04/2023 10:53
 * Scanner in = new Scanner(System.in);
 */

import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddress {
    static final int SEG_COUNT = 4;   // 4节IP
    private List<String> res;
    private int[] segments;  // 存储每一节的ip

    public List<String> restoreIpAddresses(String s) {
        res = new LinkedList<>();
        segments = new int[SEG_COUNT];
        char[] ch = s.toCharArray();
        restoreIP(ch, 0, 0);
        return res;
    }

    public void restoreIP(char[] ch, int segID, int segStart) {
        // 如果已经找到四节
        if (segID == SEG_COUNT) {
            // 如果下一次开始的位置等于字符串的长度，说明找到一个有效答案
            if (segStart == ch.length) {
                // 开始拼接
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; i++) {
                    sb.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        sb.append('.');
                    }
                }
                res.add(sb.toString());
            }
            // 如果开始的位置不等于字符串长度，说明这次的结果不合法，直接return
            return;
        }

        // 剪枝1：如果还没有找完4段IP就已经遍历完字符串，那么直接return
        if (segStart == ch.length) {
            return;
        }

        // 剪枝2：每节IP地址不能有前导0，所以如果是0，那么这一段只能是0
        if (ch[segStart] == '0') {
            segments[segID] = 0;
            restoreIP(ch, segID + 1, segStart + 1);
        }

        // 一般情况
        int address = 0;
        for (int segEnd = segStart; segEnd < ch.length; segEnd++) {
            address = address * 10 + (ch[segEnd] - '0');
            if (address > 0 && address <= 0xFF) {
                segments[segID] = address;
                restoreIP(ch, segID + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
