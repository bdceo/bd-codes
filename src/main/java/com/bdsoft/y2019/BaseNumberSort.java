package com.bdsoft.y2019;

import java.util.Arrays;

/**
 * 基数排序，参考：
 * https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651961634&idx=1&sn=1e9617d6f6bd2790eabedca22ea49879&chksm=bd2d0cfe8a5a85e8dd52dd0453abe7118932f3dd0068682c6829c37d69e197acfe0efff051e7&scene=21#wechat_redirect
 */
public class BaseNumberSort {

    /**
     * 基数排序的两个关键要点：
     * <p>
     * （1）基：被排序的元素的“个位”“十位”“百位”，暂且叫“基”，栗子中“基”的个数是2（个位和十位）；
     * <p>
     * （2）桶：“基”的每一位，都有一个取值范围，栗子中“基”的取值范围是0-9共10种，所以需要10个桶（bucket），来存放被排序的元素；
     */
    public static void main(String[] args) {
        int[] src = {2, 11, 82, 2, 44, 13, 7, 5, 54, 28, 9, 56};
//        int[] src = {72, 11, 82, 32, 44, 13, 17, 95, 54, 28, 79, 56};
        print(src, "原始：");

        // 元素基数，个位：72%10=2
        src = bucketSort(src, (n) -> n % 10);
        print(src, "取个位，排序：");

        // 元素基数，十位：72/10=7
        src = bucketSort(src, (n) -> n / 10);
        print(src, "取十位，排序：");
    }

    /**
     * 桶排序
     *
     * @param src 原始数据
     * @param bn  基数位-计算桶坐标
     * @return
     */
    static int[] bucketSort(int[] src, BaseNumber bn) {
        int[][] bucket = new int[10][];
        for (int n : src) {
            int index = bn.getBase(n);
            if (bucket[index] == null) {
                bucket[index] = new int[]{n};
            } else {
                int subLen = bucket[index].length;
                int[] sub = Arrays.copyOf(bucket[index], subLen + 1);
                sub[subLen] = n;
                bucket[index] = sub;
            }
        }
        src = two2one(bucket, src.length);
        return src;
    }

    /**
     * 二维转一维
     *
     * @param bucket 桶数据
     * @param total  一维长度
     * @return
     */
    static int[] two2one(int[][] bucket, int total) {
        int[] src = new int[total];
        int i = 0;
        for (int[] ints : bucket) {
            if (ints != null) {
                for (int a : ints) {
                    src[i++] = a;
                }
            }
        }
        return src;
    }

    static void print(int[] arr, String info) {
        System.out.println(info);
        Arrays.stream(arr).forEach(t -> System.out.print(t + " "));
        System.out.println();
    }

}

/**
 * 取基数：个位，十位
 */
@FunctionalInterface
interface BaseNumber {
    int getBase(int n);
}
