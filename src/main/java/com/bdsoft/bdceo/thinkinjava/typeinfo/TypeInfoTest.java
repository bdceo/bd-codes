package com.bdsoft.bdceo.thinkinjava.typeinfo;

import java.util.AbstractList;
import java.util.ArrayList;

public class TypeInfoTest {

	/**
	 * java运行时类型信息：RTTI，反射
	 */
	public static void main(String[] args) throws Exception {

		Class cls = Class
				.forName("com.bdsoft.bdceo.thinkinjava.excepion.ExceptionTest");

		Object obj = cls.newInstance();
		obj.getClass();

		cls = TypeInfoTest.class;

		cls = Integer.TYPE;

		boolean t = TypeInfoTest.class.isAssignableFrom(SubInfo.class);
		System.out.println(t);
		t = TypeInfoTest.class.isAssignableFrom(TypeInfoTest.class);
		System.out.println(t);
	}
	
	

	class SubInfo extends TypeInfoTest {
	}

}
