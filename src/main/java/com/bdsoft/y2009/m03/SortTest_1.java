package com.bdsoft.y2009.m03;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortTest_1 {

	public static Set<String> set = new TreeSet<String>();

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		System.out.println(list);

		List<String> rt = find(list);
		System.out.println(rt);
		System.out.println(rt.size());

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

	public static List<String> find(List<String> list) {
		List<String> rtn = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			System.out.println("str=" + str);
			list.remove(i);
			if (list.size() == 0) {
				rtn.add(str);
			} else {
				List<String> sList = find(list);
				System.out.println("sList=" + sList);
				for (String s : sList) {
					rtn.add(str + s);
					if (s.length() == 5) {
						addNumber(str + s);
					}
				}
				System.out.println("rtn=" + rtn);
			}
			list.add(i, str);
			System.out.println("list=" + list);
			System.out.println("-----------------------");
		}
		return rtn;
	}

	public static void addNumber(String str) {
		if (str.charAt(2) == '4' || str.contains("35") || str.contains("53")) {
			return;
		}
		set.add(str);
	}

}