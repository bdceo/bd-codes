package com.bdsoft;

import java.util.Arrays;

/**
 * 功能
 *
 * @author 丁辰叶
 * @version 1.0
 * @date 2018/3/14 16:14
 */
public class Ms0314 {

    public static void main(String[] args) {
        int[] days = new int[]{6, 10, 20, 14, 4, 9, 20, 10, 17, 12};

        // 最小，最大
        int min = days[0], max = days[0];
        // 买入，卖出
        int in = 1, out = days.length;
        for (int i = 1; i < days.length; i++) {
            int dp = days[i];
            if (dp < min) {
                min = dp;
                in = i + 1;
                continue;
            }
            if (dp >= max) {
                max = dp;
                out = i + 1;
            }
        }

        Arrays.stream(days).forEach(i -> System.out.print(i + " "));
        String res = String.format("\n建议：\n\t第%d天以%d元买入\n\t第%d天以%d元卖出\n\t可盈利差价%d元", in, min, out, max, max - min);
        System.out.println(res);
    }
}
