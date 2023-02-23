// https://leetcode.cn/problems/longest-palindromic-substring/
package String;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LongestPalindrome {

    public char[] toMarcher(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : chars[i / 2];
        }
        return res;
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        // 先将字符串转换成Marcher形式
        // #d#f#
        char[] mStr = toMarcher(s);
        int C = -1, R = -1;
        int resR = -1, resC = -1;
        int mLen = mStr.length;
        int[] pArr = new int[mLen];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pArr.length; i++) {
            // 先把一定回文的区域赋值
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 然后往外扩
            while (i + pArr[i] < mLen && i - pArr[i] > -1) {
                if (mStr[i + pArr[i]] == mStr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 扩充结束后，i位置的值就是mStr为中心的最长回文半径
            // 更新R和C
            if (R < pArr[i] + i) {
                R = pArr[i] + i;
                C = i;
            }
            // 更新最大长度,中心
            if (max < pArr[i]) {
                max = pArr[i];
                resR = R;
                resC = C;
            }
        }
        // max -1 是回文串的长度
        return getRes(mStr, resC, resR, max - 1);
    }

    public String getRes(char[] mStr, int C, int R, int len) {
        StringBuilder sb  = new StringBuilder();
        for (int i = C - len; i < R; i++) {
            if ((i & 1) == 1) {
                sb.append(mStr[i]);
            }
        }
        return sb.toString();
    }


    // 2023.2.19
    public char[] getMStr(String str) {
        char[] ch = str.toCharArray();
        char[] mStr = new char[2 * ch.length + 1];
        for (int i = 0; i < mStr.length; i++) {
            mStr[i] = (i & 1) == 0 ? '#' : ch[i / 2];
        }
        return mStr;
    }

    public String getLongestLCP(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 处理后的mStr
        char[] mStr = getMStr(s);
        // 回文半径数组
        // 对于mStr来说，pArr[i]表示以i为中心的回文字符串的半径
        // 对于s来说，pArr[i]表示以i为中心的回文字符串的长度
        int[] pArr = new int[mStr.length];
        // C：当前最长的回文字符串的中心
        // R：当前C表示的回文字符串的右边界，注意不包含R位置，也即开区间
        int C = -1, R = -1;
        // 这里的C和R记录结果的位置
        int resC = -1, resR = -1;
        // 找到的最大的长度
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != mStr.length; i++) {
            // 在四种条件下必然回文的区域
            // 2 * C - i就是i关于C对称的位置i‘
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            // i + pArr[i]表示i位置为中心的回文字符串的右边界
            // i - pArr[i]为左边界
            // 在两者都不越界的情况下往外扩
            while (i + pArr[i] < mStr.length && i - pArr[i] > -1) {
                // 如果左右边界相等，i位置的答案++
                if (mStr[i + pArr[i]] == mStr[i - pArr[i]]) {
                    pArr[i]++;
                    // 这里不能R++，因为有可能i比R小
//                    R++;
                } else {
                    break;
                }
            }
            // i位置更新后，判断R是否增大
            // 只有R增加的时候max才会更新
            if (i + pArr[i] > R) {
                C = i;
                R = i + pArr[i];

            }
            if (pArr[i] > max) {
                max = pArr[i];
                resC = i;
                resR = R;
            }
        }
        // 注意需要减去1
        return getRes(mStr, resC, resR, max - 1);
    }

    public static void main(String[] args) {
        LongestPalindrome obj = new LongestPalindrome();
        char[] res = obj.getMStr("12");
        System.out.println(Arrays.toString(res));
//        System.out.println(obj.longestPalindrome("babad"));
    }
}
