package com.bdsoft.y2011;

import java.util.Arrays;

public class TestSort {

    /**
     * 冒泡排序，字符串反转
     */
    public static void main(String[] args) {
        int[] its = new int[]{31, 22, 43, 11, 34, 41, 25, 69, 42, 39};
        maopao1(Arrays.copyOf(its, its.length));
        maopao2(Arrays.copyOf(its, its.length));
//		reverse();
//        sushu();// 计算素数
    }

    /**
     * 计算质数、素数：大于1，只能被自身除尽
     */
    public static void sushu() {
        int[] su = new int[70];
        int size = 0;
        boolean flag;
        for (int i = 100; i < 500; i++) {
            flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                su[size] = i;
                size++;
            }
        }
        for (int i = 0; i < su.length; i++) {
            System.out.print(su[i] + " ");
            if (i % 9 == 0)
                System.out.println();
        }
        System.out.println("\n\n100-500之间素数个数：" + size);
    }

    /**
     * 字符串反转，利用中间字符，前后相应位置翻转
     */
    public static void reverse() {
        System.out.println("字符串反转-1");
        String str = "abcde";
        System.out.println(str);
        // 首尾互换
        char[] cs = str.toCharArray();
        int len = cs.length;
        for (int i = 0; i < len / 2; i++) {
            char c = cs[i];
            cs[i] = cs[len - 1 - i];
            cs[len - 1 - i] = c;
        }
        System.out.println(cs);
    }

    /**
     * 冒泡-1
     */
    public static void maopao1(int[] its) {
        System.out.println("冒泡排序-1");
        int ct = 0;
        int len = its.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (its[j] > its[j + 1]) {
                    ct++;
                    int temp = its[j + 1];
                    its[j + 1] = its[j];
                    its[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(its) + "\t" + ct);
    }

    /**
     * 冒泡-2
     */
    public static void maopao2(int[] its) {
        System.out.println("冒泡排序-2");
        int ct = 0;
        int low = -1;
        int high = its.length;
        while (low <= high) {
            low++;
            high--;
            for (int j = low; j < high; j++) {
                if (its[j] > its[j + 1]) {
                    System.out.print("low ");
                    ct++;
                    int temp = its[j];
                    its[j] = its[j + 1];
                    its[j + 1] = temp;
                }
            }
            for (int j = high; j > low; j--) {
                if (its[j - 1] > its[j]) {
                    System.out.print("high ");
                    ct++;
                    int temp = its[j - 1];
                    its[j - 1] = its[j];
                    its[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(its) + "\t" + ct);
    }
}
