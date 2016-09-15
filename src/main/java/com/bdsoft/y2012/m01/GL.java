package com.bdsoft.y2012.m01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GL {

	private static Random RANDOM = new Random();

	public static void main(String[] args) {
//		gl1();
		gl2();
	}

	public static void gl1() {
		List list = new ArrayList();
		list.add(new Integer(25));
		list.add(new Integer(40));
		list.add(new Integer(35));
		Collections.sort(list);
		System.out.println(list);

		int a = ((Integer) list.get(0)).intValue();
		int b = ((Integer) list.get(1)).intValue();
		int c = ((Integer) list.get(2)).intValue();
		System.out.println("几率最大的数：" + c);
		System.out.println("中间数：" + (c + b));
		Map map = new HashMap();
		int x = 0;
		int y = 0;
		int z = 0;
		for (int i = 0; i < 1000000; i++) {
			int k = RANDOM.nextInt();
			int j = Math.abs(k % 100);
			if (j <= c) {
				map.put("0", new Integer(++x));
			} else if (j > c && j <= (c + b)) {
				map.put("1", new Integer(++y));
			} else if (j > (c + b) && j < (a + b + c)) {
				map.put("2", new Integer(++z));
			}

		}
		System.out.println(map);
	}

	public static void gl2() {
		int a = 50;
		int b = 35;
		int c = 15;

		int a2 = 0;
		int b2 = 1;
		int c2 = 2;

		Map result = new LinkedHashMap();

		for (int i = 0; i < a; i++) {
			result.put("" + i, "" + a2);
		}
		for (int i = a; i < a + b; i++) {
			result.put("" + i, "" + b2);
		}
		for (int i = a + b; i < a + b + c; i++) {
			result.put("" + i, "" + c2);
		}

		System.out.println("Result:" + result);

		System.out.println("随机数:"
				+ result.get("" + (int) (Math.random() * 100)));
	}

}
