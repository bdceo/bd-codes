package com.bdsoft.bdceo.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * 
 * @author bdceo
 * 
 */
public class RuntimeConstantPoolOOM {

	// 注意在：Java6 和 Java7中的不同，在Java7中，不会出现oom
	// 在java7中，true、false；java6中，false、false
	public static void main(String[] args) {

		// demo-1
		// List<String> list = new ArrayList<String>();
		// int i = 0;
		// // String.intern()
		// while (true) {
		// list.add(String.valueOf(i++).intern());
		// }
		/**
		 * 
		 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
		 * at java.lang.String.intern(Native Method) at
		 * com.bdsoft.bdceo.jvm.oom.
		 * RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
		 */

		// demo-2
		String str1 = new StringBuilder("分布式").append("计算").toString();
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str2);

	}

}
