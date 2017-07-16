/**
 * Enums.java bd-codes Copyright (c) 2016, bdsoft版权所有.
 */
package com.bdsoft.bdceo.thinkinjava.enums;

import java.util.Random;

/**
 * 19.6--随机选取
 * 
 * @author bdceo
 * @date 2016-12-29 下午12:43:15
 * @version V1.0
 */
public class Enums {

	private static Random rand = new Random(1229);

	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}
