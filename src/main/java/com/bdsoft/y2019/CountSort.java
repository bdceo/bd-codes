package com.bdsoft.y2019;

import java.util.Arrays;

/**
 * 计数排序，参考：
 * https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651961665&idx=1&sn=b7a6d0ca45a0b91801778baec0f759c6&chksm=bd2d0c9d8a5a858b0fc54dbc08d75ecdb4f11383a97222aede422c9f72a7c0240d82e5833aec&scene=21#wechat_redirect
 */
public class CountSort {

    /**
     * 步骤一：扫描待排序数据arr[N]，使用计数数组counting[MAX-MIN]，对每一个arr[N]中出现的元素进行计数；
     * <p>
     * 步骤二：扫描计数数组counting[]，还原arr[N]，排序结束；
     */
    public static void main(String[] args) {
        int[] src = new int[]{5, 3, 7, 1, 8, 2, 9, 4, 7, 2, 6, 6, 2, 6, 6};
//        int[] src = new int[]{15, 32, 27, 14, 85, 21, 91, 43, 74, 22, 62, 61, 21, 61, 61};
        print(src, "原始数据：");

        // 数值在0-100之间
        int[] counter = new int[100];
        for (int n : src) {
            counter[n] = counter[n] + 1;
        }
        print(counter, "计数结果：");

        int k = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > 0) {
                for (int j = 0; j < counter[i]; j++) {
                    src[k] = i;
                    k++;
                }
            }
        }
        print(src, "排序后：");
    }

    static void print(int[] arr, String info) {
        System.out.println(info);
        Arrays.stream(arr).forEach(t -> System.out.print(t + " "));
        System.out.println();
    }

}
