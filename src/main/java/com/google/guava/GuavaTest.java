/**
 * GuavaTest.java
 * com.bdsoft.google.guava
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.google.guava;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

/**
	https://code.google.com/p/guava-libraries/wiki/GuavaExplained?tm=6
	https://code.google.com/p/guava-libraries/wiki/GuavaExplained
	http://docs.guava-libraries.googlecode.com/git-history/release/javadoc/index.html
 */
public class GuavaTest {

	public static void main(String[] args) {
		// 数值类型比较
		int comp = Ints.compare(1, 4);
		print(comp);

		// 字符串拼接
		Joiner joiner = Joiner.on("; ").skipNulls();
		String str = joiner.join("bd", "ceo", null, "cto");
		print(str);

		str = Joiner.on("_").join(Arrays.asList(1, 3, 4));
		print(str);

		// 字符串分隔
		Iterable<String> strIte = Splitter.on(",").trimResults().omitEmptyStrings().split("foo,bar,,   qux");
		Iterator<String> strIto = strIte.iterator();
		while (strIto.hasNext()) {
			print(strIto.next());
		}

	}

	public static void print(Object o) {
		System.out.println(o.toString());
	}

}
