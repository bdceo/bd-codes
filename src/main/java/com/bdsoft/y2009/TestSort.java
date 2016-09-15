/**
 * TestSort.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.y2009;

import java.util.Arrays;

/**
 * 测试排序
 * 
 * @author bdceo
 * @date 2016-8-21 上午10:20:39
 * @version V1.0
 */
public class TestSort {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		int[] its = new int[] { 31, 22, 43, 11, 34, 41, 25, 69, 42, 39 };
		maopao1(Arrays.copyOf(its, its.length));
		maopao2(Arrays.copyOf(its, its.length));
		revers1();
	}

	// 字符串反转
	public static void revers1() {
		System.out.println("字符串反转-1");
		String str = "abcdefghijklmnopqrstuvwxyzABC";
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

	// 冒泡排序
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
					ct++;
					int temp = its[j];
					its[j] = its[j + 1];
					its[j + 1] = temp;
				}
			}
			for (int j = high; j > low; j--) {
				if (its[j - 1] > its[j]) {
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
