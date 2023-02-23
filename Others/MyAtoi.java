package Others;// https://leetcode.cn/problems/string-to-integer-atoi/

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyAtoi {
    /**
     * 将字符串中合法的数转换成double类型返回
     * 保证字符串中存在唯一的合法数字
     * @param s s
     * @return  s
     */
    public double myAtoiDouble(String s) {

        int lenS = s.length();
        int[] nums = new int[lenS];
        boolean isDecimal = false;
        boolean isPositive = true;  // 默认是正数
        double res = 0;
        int start = 0;
        char[] chars = s.toCharArray();
        // 先忽略空格和其他字符，标记正负
        // 如果是小数，应该在标记是小数，
        for (int i = 0; i < lenS; i++) {
            if (chars[i] == '-') {
                // -0.123  -2.3
                if (chars[i + 1] - '0' == 0 && chars[i + 2] == '.'
                        || Character.isDigit(chars[i + 1])) {
                    isPositive = false;
                    start = i + 1;
                    break;
                }
            }
            if (chars[i] - '0' == 0 && chars[i + 1] == '.'
                    || Character.isDigit(chars[i])) {   // 0.2
                start = i;
                break;
            }
        }
        System.out.println(start);
        // 从数字开始的位置遍历
        int lenNonDecimal = 0;
        int lenDecimal = 0;
        for (int i = start; i < lenS; i++) {
            if (chars[i] == '.') {
                nums[i - start] = -1;
                isDecimal = true;
            } else if (isDecimal && Character.isDigit(chars[i])) {  // 小数部分
                nums[i - start] = chars[i] - '0';
                lenDecimal++;
            } else if (!isDecimal && Character.isDigit(chars[i])) {
                nums[i - start] = chars[i] - '0';
                lenNonDecimal++;
            } else {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
        System.out.println("lenNonDecimal: " + lenNonDecimal);
        System.out.println("lenDecimal: " + lenDecimal);
        if (lenDecimal >= 31) {
            return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        // 最后将nums数组中的数都串起来
        int pows = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                continue;
            }
            if (lenNonDecimal > 0) {
                res += nums[i] * Math.pow(10, lenNonDecimal - 1);
                lenNonDecimal--;
            } else if (pows <= lenDecimal) {
                res += nums[i] * Math.pow(10, -pows);
                pows++;
            }
        }
        BigDecimal bd = new BigDecimal(res);
        res = bd.setScale(lenDecimal, BigDecimal.ROUND_HALF_UP).doubleValue();

        return isPositive ? res : -res;
    }

    /**
     * 不考虑小数
     * @param s s
     * @return ff
     */
    public int myAtoiInt(String s) {
        char[] chars = s.toCharArray();
        int[] nums = new int[s.length()];
        boolean isPositive = true;
        int lenNum = 0;
        int start = 0;
        int res = 0;
        // 处理空格和符号
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') {
                continue;
            }

            if (chars[i] == '-' && Character.isDigit(chars[i + 1])) {
                isPositive = false;
                start = i + 1;
                break;
            } else if (chars[i] == '+' && Character.isDigit(chars[i + 1])) {
                start = i + 1;
                break;
            } else if (Character.isDigit(chars[i])){
                start = i;
                break;
            } else {
                break;
            }
        }

        System.out.println("start: " + start);
        // 处理数字
        for (int i = start; i < s.length(); i++) {
            if (Character.isDigit(chars[i])) {
                System.out.println("i: " + i + "   chars[i]: " + chars[i]);
                nums[i - start] = chars[i] - '0';
                lenNum++;
            } else {
                break;
            }
        }
        System.out.println("lenNum: " + lenNum);
        System.out.println(Arrays.toString(nums));

        int index = 0;
        while (index < lenNum) {
            // res * 10  每越界且相加后也每越界
            if (res > Integer.MAX_VALUE / 10 || res > (Integer.MAX_VALUE - nums[index]) / 10) {
                return isPositive ? Integer.MAX_VALUE :Integer.MIN_VALUE;
            } else {
                res = (res * 10 + nums[index++]);
                System.out.println("res: " + res);
            }
        }

        return isPositive ? res : -res;
    }

    /**
     * 正则表达式解法
     * @param s s
     * @return s
     */
    public int myAtoiRE(String s) {
        // 清空字符串开头的空格
        s = s.trim();
        Pattern p = Pattern.compile("^[]\\+\\-]?\\d+");
        Matcher m = p.matcher(s);
        int value = 0;
        // 判断能否匹配
        if (m.find()) {
            try {
                value = Integer.parseInt(s.substring(m.start(), m.end()));
            } catch (Exception e){
                value = s.charAt(0) == '-' ? -value : value;
            }
        }
        return value;
    }

    public static void main(String[] args) {
        MyAtoi obj = new MyAtoi();
//        System.out.println(obj.myAtoiDouble("     -0.4193 with words"));
//        System.out.println(obj.myAtoiInt("42"));
        System.out.println(obj.myAtoiRE("-000000042"));
    }
}
