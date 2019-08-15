package com.bdsoft.y2015;

/**
 * 进制转换
 */
public class DecimalFormat {

    // 32位
    final static char[] digits = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        String t = numericToString(-19880517, 16);
        System.out.println(t);

        int n = stringToNumeric(t, 16);
        System.out.println(n);
    }

    /**
     * 数字转字符串格式的进制表达
     *
     * @param i      数字
     * @param system 进制
     * @return
     */
    public static String numericToString(int i, int system) {
        long num = 0;
        if (i < 0) {
            num = ((long) 2 * 0x7fffffff) + i + 2;
        } else {
            num = i;
        }
        char[] buf = new char[32];
        int charPos = 32;
        while ((num / system) > 0) {
            buf[--charPos] = digits[(int) (num % system)];
            num /= system;
        }
        buf[--charPos] = digits[(int) (num % system)];
        return new String(buf, charPos, (32 - charPos));
    }

    /**
     * 字符串进制转原始数字
     *
     * @param s      字符串
     * @param system 进制
     * @return
     */
    public static int stringToNumeric(String s, int system) {
        char[] buf = new char[s.length()];
        s.getChars(0, s.length(), buf, 0);
        long num = 0;
        for (int i = 0; i < buf.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                if (digits[j] == buf[i]) {
                    num += j * Math.pow(system, buf.length - i - 1);
                    break;
                }
            }
        }
        return (int) num;
    }

}
