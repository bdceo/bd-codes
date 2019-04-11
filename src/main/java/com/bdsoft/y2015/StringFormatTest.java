/**
 * StringFormatTest.java
 * com.bdsoft.y2014.m11
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.y2015;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 
 * @author	丁辰叶
 * @date	2014-11-19
 */
public class StringFormatTest {

	private static String MARKED_BY_CERTAIN_TAG_KEY = "{0}:{1}:{2}:marked";

	public static void main(String[] rags) {
		String msg = format(MARKED_BY_CERTAIN_TAG_KEY, 13390622165760005L, 0, 6);
		System.out.println(msg);
	}

	private static String format(final String format, final Object... args) {
		if (format == null || format.length() == 0) {
			return "";
		}
		if (args == null) {
			return format;
		}
		try {
			return new MessageFormat(format, Locale.getDefault()).format(args);
		} catch (Exception e) {
			throw new RuntimeException("字符串格式化错误", e);
		}
	}
}
