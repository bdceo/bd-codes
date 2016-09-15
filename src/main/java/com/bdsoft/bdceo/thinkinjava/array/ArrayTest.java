/**
 * ArrayTest.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.array;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 数组测试
 * 
 * @author bdceo
 * @date 2016-8-16 上午8:04:21
 * @version V1.0
 */
public class ArrayTest<T> {

	static Random rd = new Random(System.currentTimeMillis());

	T[] def;// 声明泛型数组，没问题

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		// 数组定义
		int[] ia1 = null;
		int[] ia2 = new int[3];
		int[] ia3 = new int[] { 2, 2, 2, 1, };
		int[] ia4 = { 1, 2, 3 };
		// 打印数组
		ia1 = new int[rd.nextInt(100)];
		Arrays.fill(ia1, rd.nextInt(100));
		Arrays.fill(ia1, Math.max(0, ia1.length - 10), ia1.length, rd.nextInt(10));
		show(ia1);
		show(ia2);
		show(ia3);
		// 修改数组
		show(ia4);
		System.out.println(ia4);
		change(ia4);
		show(ia4);
		System.out.println("\n");

		// 多维数组
		int[][][] ia5 = new int[2][4][5];
		System.out.println(Arrays.deepToString(ia5));
		int[][][] ia6 = new int[rd.nextInt(10)][][];
		for (int i = 0; i < ia6.length; i++) {
			ia6[i] = new int[rd.nextInt(10)][];
			for (int j = 0; j < ia6[i].length; j++) {
				ia6[i][j] = new int[rd.nextInt(10)];
				for (int k = 0; k < ia6[i][j].length; k++) {
					ia6[i][j][k] = rd.nextInt(100);
				}
			}
		}
		System.out.println(Arrays.deepToString(ia6));
		ArrayTest[][] atarr = { { new ArrayTest() }, { new ArrayTest(), new ArrayTest() } };
		System.out.println(Arrays.deepToString(atarr));
		Integer[][] ia7 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		System.out.println(Arrays.deepToString(ia7));
		show(ia7, true);
	}

	static void show(int[] arr) {
		if (arr != null) {
			System.out.println("\n\n长度：" + arr.length);
			System.out.print("内容：");
			for (int a : arr) {
				System.out.print(a);
				System.out.print(" ");
			}
		}
	}

	static <K> void show(K[] karr, boolean fx) {
		if (karr != null) {
			System.out.println("\n\n长度：" + karr.length);
			System.out.print("内容：");
			for (K a : karr) {
				System.out.print(a);
				System.out.print(" ");
			}
		}
	}

	static void change(int[] arr) {
		System.out.println(arr);
		arr[1] = 100;
	}

}
