package com.bdsoft.y2010.m08;

import java.util.ArrayList;
import java.util.List;

public class ListAddTest {

	public static void main(String[] args) {
		List a = new ArrayList();
		a.add("a1");
		a.add("a2");
		List b = new ArrayList();
		b.add("b1");
		b.add("b2");
		a.addAll(b);
		for (int i = 0; i < a.size(); i++) {
			System.out.println((String) a.get(i));
		}
	}

}
