/**
 * TestList.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.y2009;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 集合容器测试
 * 
 * @author bdceo
 * @date 2016-8-21 上午9:23:50
 * @version V1.0
 */
public class TestList {

	/**
	 * 入口
	 */
	public static void main(String[] args) {
		List list = new ArrayList();

		for (int ctr = 0; ctr < 4; ctr++) {
			list.add(new Integer(ctr));
		}
		list.add("Martina");
		list.add("Serena");
		list.add("Venus");
		list.add("Serena");
		System.out.println(list);

		System.out.println("Serena 在 " + list.indexOf("Serena"));
		System.out.println("Martina 在 " + list.lastIndexOf("Martina"));

		List subList = list.subList(4, list.size());
		System.out.println(subList);

		Collections.shuffle(list);
		System.out.println(list);
	}

}
