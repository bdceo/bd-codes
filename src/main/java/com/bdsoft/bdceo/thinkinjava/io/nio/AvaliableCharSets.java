/**
 * AvaliableCharSets.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

/**
 * 系统有效字符编码及别名
 * 
 * @author bdceo
 * @date 2016-12-28 下午1:49:32
 * @version V1.0
 */
public class AvaliableCharSets {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while (it.hasNext()) {
			String csName = it.next();
			System.out.print(csName);

			Iterator<String> aliases = charSets.get(csName).aliases().iterator();
			if (aliases.hasNext())
				System.out.print(" : ");
			while (aliases.hasNext()) {
				System.out.print(aliases.next());
				if (aliases.hasNext()) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}

}
