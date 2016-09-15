package com.bdsoft.y2009.m03;

import java.util.Set;
import java.util.TreeSet;

public class SortTest_2 {

	public static Set<String> set = new TreeSet<String>();

	public static void main(String args[]) {
		char[] number = new char[] { '1', '2', '2', '3', '4', '5' };
		perm(number, 0, number.length - 1);
		System.out.println(set.size());
		int cols = 10;
		for (String s : set) {
			System.out.print(s + " ");
			if (cols-- == 1) {
				System.out.println();
				cols = 10;
			}
		}
	}

	public static void perm(char[] n, int beg, int end) {
		if (beg == end) {
			addNumber(String.valueOf(n));
		} else {
			for (int i = beg; i <= end; ++i) {
				swap(n, beg, i);
				perm(n, beg + 1, end);
				swap(n, beg, i);
			}
		}
	}

	public static void swap(char[] n, int x, int y) {
		if (x == y || n[x] == n[y]) {
			return;
		}
		char temp = n[x];
		n[x] = n[y];
		n[y] = temp;
	}

	public static void addNumber(String str) {
		if (str.charAt(2) == '4' || str.contains("35") || str.contains("53")) {
			return;
		}
		set.add(str);
	}
}
