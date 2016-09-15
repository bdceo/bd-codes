package com.bdsoft.bdceo.j2se.col;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class Zhan {

	public static void main(String[] args) {

		// Stack
		List list = new ArrayList();
		for (int i = 0; i < 20; i++) {
			list.add(Integer.valueOf(i));
		}
		System.out.println(list);
		Stack stack = new Stack();
		for (int i = 0; i < 20; i++) {
			stack.push(Integer.valueOf(i));
		}
		for (int i = 0; i < 20; i++) {
			list.set(i, stack.pop());
		}
		System.out.println(list);

		// Deque
		Deque ds = new ArrayDeque();
		String[] sa = new String[] { "1", "a", "2", "b", "3", "c", "4", "d" };
		for (String s : sa) {
			ds.push(s);
		}
		while (ds.size() != 0) {
			System.out.print((String) ds.pop() + " ");
		}
		System.out.println();

		// sort and search
		List ls = new ArrayList();
		for (int i = 0; i < 50; i++) {
			ls.add(Integer.valueOf((int) (Math.random() * 100)));
		}
		System.out.println(ls);
		Collections.sort(ls);
		System.out.println(ls);
		int index = Collections.binarySearch(ls, Integer.valueOf(4));
		System.out.println(index > 0 ? "这此随机生成的数中有4" : "这次随机生成的没有4.");

		// shuffle
		List pk = new ArrayList();
		for (int i = 1; i < 14; i++) {
			String s = null;
			switch (i) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				s = String.valueOf(i + 1);
				break;
			case 10:
				s = "J";
				break;
			case 11:
				s = "Q";
				break;
			case 12:
				s = "K";
				break;
			case 13:
				s = "A";
				break;
			}
			pk.add("红桃" + s);
			pk.add("方片" + s);
			pk.add("黑桃" + s);
			pk.add("梅花" + s);
		}
		pk.add("大王");
		pk.add("小王");
		System.out.println(pk);
		Collections.shuffle(pk);
		System.out.println(pk);

		// copy
		List la = new ArrayList();
		List lb = new ArrayList();
		la.add(Integer.valueOf(-1));
		for (int i = 0; i < 10; i++) {
			la.add(Integer.valueOf(i));
			lb.add(Integer.valueOf(i + 100));
		}
		la.add(Integer.valueOf(10));
		System.out.println(la);
		Collections.copy(la, lb);
		System.out.println(la);
	}
}
