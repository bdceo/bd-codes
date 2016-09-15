package com.bdsoft.y2012.m11;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("bdceo");
		list.add("bdcoo");
		while (list.size() > 0) {

			System.out.println(list.get(0));
			try {
				throw new Exception("haha");
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}

	}

}
