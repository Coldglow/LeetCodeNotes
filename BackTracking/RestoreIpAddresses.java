package BackTracking;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    private List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return this.res;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        restore(0, sb, 1, chars);
        return this.res;
    }

    public void restore(int index, StringBuilder sb, int part, char[] ch) {
        if (part == 3) {
            if (isValid(ch, index)) {
                restore(index, sb, part + 1, ch);
            }
            return;
        }
        for (int i = index; i < ch.length; ++i) {
            sb.append(ch[i]);
            sb.append('.');
            restore(i + 1, sb, part + 1, ch);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean isValid(char[] ch, int i) {
        return false;
    }

    public static void main(String[] args) {
        RestoreIpAddresses o = new RestoreIpAddresses();
        List<String> res = o.restoreIpAddresses("25525511135");
        for (String str : res) {
            System.out.println(str);
        }
    }
}
