// https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
package String;

public class ReplaceSpace {
    public String replaceSpace(String s) {
        int spaceNum = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ' ') {
                spaceNum++;
            }
        }
        char[] res = new char[len + spaceNum * 2];
        System.out.println(len + spaceNum * 3);
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                res[index++] = '%';
                res[index++] = '2';
                res[index++] = '0';
            } else {
                res[index++] = s.charAt(i);
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        ReplaceSpace o = new ReplaceSpace();
        System.out.println(o.replaceSpace("We are happy."));
    }
}
