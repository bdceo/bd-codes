/**
 * NetPackageClassTest.java
 * com.bdsoft.bdceo.j2se.net
 * Copyright (c) 2014, 北京微课创景教育科技有限公司版权所有.
*/
package com.bdsoft.bdceo.j2se.net;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * 
 * @author	丁辰叶
 * @date	2015-11-24
 */
public class NetPackageClassTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		InetAddress ia = InetAddress.getByName("vko.cn");
		
		System.out.println(ia);
		
		byte[] LOCAL = new byte[ ] { 127, 0, 0, 1 };
		boolean local = Arrays.equals(LOCAL, ia.getAddress());
		System.out.println(local);
		
		System.out.println(ia.getAddress()[0]);
		System.out.println(ia.getAddress()[1]);
		System.out.println(ia.getAddress()[2]);
		System.out.println(ia.getAddress()[3]);

	}

}
